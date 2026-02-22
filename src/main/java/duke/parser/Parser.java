package duke.parser;

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
            throw new DukeException("OOPS! Sorry I don't understand this :(");
        }


    }

    public static Command parseTodo(String fullCommand) throws DukeException {
        if (fullCommand.length() < 5) {
            throw new DukeException("OOPS! Seems like you did not enter a description for todo ;-; Please enter a todo with description");
        }
        int firstSpace = fullCommand.indexOf(" ");
        String description = fullCommand.substring(firstSpace + 1);

        return new TodoCommand(description);
    }

    public static Command parseDeadline(String fullCommand) throws DukeException {
        int firstSpace = fullCommand.indexOf(" ");
        if (!fullCommand.contains("/by ")) {
            throw new DukeException("Please enter deadline in the correct format (with /by).");
        }
        int byIndex = fullCommand.indexOf("/by ");
        String description = fullCommand.substring(firstSpace + 1, byIndex - 1);
        String by = fullCommand.substring(byIndex + 1).replace("by ", "");
        return new DeadlineCommand(description, by);
    }

    public static Command parseEvent(String fullCommand) throws DukeException {
        int firstSpace = fullCommand.indexOf(" ");
        if (!fullCommand.contains("/from") || (!fullCommand.contains("/to"))) {
            throw new DukeException("Please enter event in the correct format (with /from and /to).");
        }
        int fromIndex = fullCommand.indexOf("/from");
        int toIndex = fullCommand.indexOf("/to");
        String description = fullCommand.substring(firstSpace + 1, fromIndex - 1);
        String from = fullCommand.substring(fromIndex + 1, toIndex - 1).replace("from ", "");
        String to = fullCommand.substring(toIndex + 1).replace("to ", "");
        return new EventCommand(description, from, to);
    }

    public static Command parseDelete(String[] words) throws DukeException{
        if (words.length < 2) {
            throw new DukeException("Please specify which task you want to delete.");
        }
        int item = Integer.parseInt(words[1]) - 1;
        return new DeleteCommand(item);
    }

    public static Command parseMark(String[] words) throws DukeException{
        if (words.length < 2) {
            throw new DukeException("Please specify which task you want to mark.");
        }
        int item = Integer.parseInt(words[1]) - 1;
        return new MarkCommand(item);
    }

    public static Command parseUnmark(String[] words) throws DukeException{
        if (words.length < 2) {
            throw new DukeException("Please specify which task you want to unmark.");
        }
        int item = Integer.parseInt(words[1]) - 1;
        return new UnmarkCommand(item);
    }

}
