package duke.parser;

import static duke.common.Messages.MESSAGE_INVALID_DEADLINE_FORMAT;
import static duke.common.Messages.MESSAGE_INVALID_DELETE;
import static duke.common.Messages.MESSAGE_INVALID_EVENT_FORMAT;
import static duke.common.Messages.MESSAGE_INVALID_FIND;
import static duke.common.Messages.MESSAGE_INVALID_INPUT;
import static duke.common.Messages.MESSAGE_INVALID_MARK;
import static duke.common.Messages.MESSAGE_INVALID_TODO_FORMAT;
import static duke.common.Messages.MESSAGE_INVALID_UNMARK;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;
import duke.exception.DukeException;

/**
 * Responsible for interpreting user input and converting it
 * into executable Command objects.
 *
 * This class parses raw input strings, validates command formats,
 * and delegates the creation of specific Command subclasses.
 *
 * If the input format is invalid, a DukeException is thrown.
 */
public class Parser {

    /**
     * Parses a full user input string and returns the corresponding Command.
     *
     * The first word of the input determines the command type.
     * The remaining input is validated and delegated to
     * specialized parsing methods.
     *
     * @param fullCommand The raw input entered by the user.
     * @return A Command object representing the user's request.
     * @throws DukeException If the input is invalid or improperly formatted.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] words = fullCommand.split(" ");

        switch (words[0]) {
        case "list":
            return new ListCommand();
        case "todo":
            return parseTodo(fullCommand);
        case "deadline":
            return parseDeadline(fullCommand);
        case "event":
            return parseEvent(fullCommand);
        case "delete":
            return parseDelete(words);
        case "mark":
            return parseMark(words);
        case "unmark":
            return parseUnmark(words);
        case "bye":
            return new ByeCommand();
        case "find":
            return parseFind(words);
        default:
            throw new DukeException(MESSAGE_INVALID_INPUT);
        }
    }

    /**
     * Parses a todo command from the given input string.
     *
     * Extracts the task description and creates a TodoCommand.
     *
     * @param fullCommand The full input string.
     * @return A TodoCommand containing the extracted description.
     * @throws DukeException If the todo format is invalid.
     */
    public static Command parseTodo(String fullCommand) throws DukeException {
        if (fullCommand.length() < 5) {
            throw new DukeException(MESSAGE_INVALID_TODO_FORMAT);
        }
        int firstSpace = fullCommand.indexOf(" ");
        String description = fullCommand.substring(firstSpace + 1);

        return new TodoCommand(description);
    }

    /**
     * Parses a deadline command from the given input string.
     *
     * Extracts the task description and due date, converts the
     * date string into a LocalDateTime object, and returns
     * a DeadlineCommand.
     *
     * Expected format:
     * deadline <description> /by dd/MM/yyyy HHmm
     *
     * @param fullCommand The full input string.
     * @return A DeadlineCommand containing the parsed data.
     * @throws DukeException If the format is invalid or the date cannot be parsed.
     */
    public static Command parseDeadline(String fullCommand) throws DukeException {
        try {
            int firstSpace = fullCommand.indexOf(" ");
            if (!fullCommand.contains("/by ")) {
                throw new DukeException(MESSAGE_INVALID_DEADLINE_FORMAT);
            }
            int byIndex = fullCommand.indexOf("/by ");
            String description = fullCommand.substring(firstSpace + 1, byIndex - 1);
            String dateString = fullCommand.substring(byIndex + "/by ".length()).trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime by = LocalDateTime.parse(dateString, formatter);
            return new DeadlineCommand(description, by);
        } catch (DateTimeException e) {
            throw new DukeException("Invalid date format. Use /by dd/MM/yyyy HHmm");
        }
    }

    /**
     * Parses an event command from the given input string.
     *
     * Extracts the description, start time, and end time,
     * and returns an EventCommand.
     *
     * Expected format:
     * event <description> /from <start> /to <end>
     *
     * @param fullCommand The full input string.
     * @return An EventCommand containing the parsed data.
     * @throws DukeException If the format is invalid.
     */
    public static Command parseEvent(String fullCommand) throws DukeException {
        int firstSpace = fullCommand.indexOf(" ");
        if (!fullCommand.contains("/from") || (!fullCommand.contains("/to"))) {
            throw new DukeException(MESSAGE_INVALID_EVENT_FORMAT);
        }
        int fromIndex = fullCommand.indexOf("/from");
        int toIndex = fullCommand.indexOf("/to");
        String description = fullCommand.substring(firstSpace + 1, fromIndex - 1);
        String from = fullCommand.substring(fromIndex + 1, toIndex - 1).replace("from ", "");
        String to = fullCommand.substring(toIndex + 1).replace("to ", "");
        return new EventCommand(description, from, to);
    }

    /**
     * Parses a delete command.
     *
     * Extracts the task index and converts it to zero-based indexing.
     *
     * @param words The split input array.
     * @return A DeleteCommand with the specified index.
     * @throws DukeException If the input format is invalid.
     */
    public static Command parseDelete(String[] words) throws DukeException {
        if (words.length < 2) {
            throw new DukeException(MESSAGE_INVALID_DELETE);
        }
        int item = Integer.parseInt(words[1]) - 1;
        return new DeleteCommand(item);
    }

    /**
     * Parses a mark command.
     *
     * Extracts the task index and converts it to zero-based indexing.
     *
     * @param words The split input array.
     * @return A DeleteCommand with the specified index.
     * @throws DukeException If the input format is invalid.
     */
    public static Command parseMark(String[] words) throws DukeException {
        if (words.length < 2) {
            throw new DukeException(MESSAGE_INVALID_MARK);
        }
        int item = Integer.parseInt(words[1]) - 1;
        return new MarkCommand(item);
    }

    /**
     * Parses a unmark command.
     *
     * Extracts the task index and converts it to zero-based indexing.
     *
     * @param words The split input array.
     * @return A DeleteCommand with the specified index.
     * @throws DukeException If the input format is invalid.
     */
    public static Command parseUnmark(String[] words) throws DukeException {
        if (words.length < 2) {
            throw new DukeException(MESSAGE_INVALID_UNMARK);
        }
        int item = Integer.parseInt(words[1]) - 1;
        return new UnmarkCommand(item);
    }

    /**
     * Parses a find command.
     *
     * Extracts the search keyword and returns a FindCommand.
     *
     * @param words The split input array.
     * @return A FindCommand with the specified keyword.
     * @throws DukeException If the input format is invalid.
     */
    public static Command parseFind(String[] words) throws DukeException {
        if (words.length < 2) {
            throw new DukeException(MESSAGE_INVALID_FIND);
        }
        String item = words[1].trim();
        return new FindCommand(item);
    }

}
