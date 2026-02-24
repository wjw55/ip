package duke.parser;

import static duke.common.Messages.MESSAGE_INVALID_DEADLINE_FORMAT;
import static duke.common.Messages.MESSAGE_INVALID_DELETE;
import static duke.common.Messages.MESSAGE_INVALID_EVENT_FORMAT;
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
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;
import duke.exception.DukeException;

public class Parser {

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
        default:
            throw new DukeException(MESSAGE_INVALID_INPUT);
        }
    }

    public static Command parseTodo(String fullCommand) throws DukeException {
        if (fullCommand.length() < 5) {
            throw new DukeException(MESSAGE_INVALID_TODO_FORMAT);
        }
        int firstSpace = fullCommand.indexOf(" ");
        String description = fullCommand.substring(firstSpace + 1);

        return new TodoCommand(description);
    }

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

    public static Command parseDelete(String[] words) throws DukeException {
        if (words.length < 2) {
            throw new DukeException(MESSAGE_INVALID_DELETE);
        }
        int item = Integer.parseInt(words[1]) - 1;
        return new DeleteCommand(item);
    }

    public static Command parseMark(String[] words) throws DukeException {
        if (words.length < 2) {
            throw new DukeException(MESSAGE_INVALID_MARK);
        }
        int item = Integer.parseInt(words[1]) - 1;
        return new MarkCommand(item);
    }

    public static Command parseUnmark(String[] words) throws DukeException {
        if (words.length < 2) {
            throw new DukeException(MESSAGE_INVALID_UNMARK);
        }
        int item = Integer.parseInt(words[1]) - 1;
        return new UnmarkCommand(item);
    }

}
