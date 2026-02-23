package duke.common;

public class Messages {
    public static final String LS = System.lineSeparator();
    public static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_WELCOME = "Hello! I'm John" + LS + "What can I do for you?";
    public static final String MESSAGE_ADD = "Got it. I've added this task: ";
    public static final String MESSAGE_DELETE = "Noted. I've removed this task: ";
    public static final String MESSAGE_TASK_DOES_NOT_EXIST = "This task does not exist.";
    public static final String MESSAGE_LIST = "Here are the tasks in your list: ";
    public static final String MESSAGE_MARK = "Nice! I've marked this task as done: ";
    public static final String MESSAGE_UNMARK = "OK, I've marked this task as not done yet: ";
    public static final String MESSAGE_INVALID_INPUT = "OOPS! Sorry I don't understand this :(";
    public static final String MESSAGE_INVALID_TODO_FORMAT = "OOPS! Seems like you did not enter a description for todo ;-; Please enter a todo with description";
    public static final String MESSAGE_INVALID_DEADLINE_FORMAT = "Please enter deadline in the correct format (with /by).";
    public static final String MESSAGE_INVALID_EVENT_FORMAT = "Please enter event in the correct format (with /from and /to).";
    public static final String MESSAGE_INVALID_DELETE = "Please specify which task you want to delete.";
    public static final String MESSAGE_INVALID_MARK = "Please specify which task you want to mark.";
    public static final String MESSAGE_INVALID_UNMARK = "Please specify which task you want to unmark.";
}
