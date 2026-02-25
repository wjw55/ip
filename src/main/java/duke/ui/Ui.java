package duke.ui;

import static duke.common.Messages.LS;
import static duke.common.Messages.MESSAGE_ADD;
import static duke.common.Messages.MESSAGE_DELETE;
import static duke.common.Messages.MESSAGE_GOODBYE;
import static duke.common.Messages.MESSAGE_WELCOME;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import duke.task.Task;

/**
 * Handles user interaction.
 *
 * This class is responsible for reading user input and
 * displaying messages to the user.
 */
public class Ui {
    private final Scanner in;
    private final PrintStream out;
    private static final String DIVIDER = "===================================================";

    /**
     * Constructs a Ui object using standard input and output.
     */
    public Ui() {
        this(System.in, System.out);
    }
    /**
     * Constructs a Ui object using custom input and output streams.
     *
     * @param in  The input stream.
     * @param out The output stream.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Reads the next command from the user.
     *
     * @return The trimmed user input.
     */
    public String readCommand() {
        return in.nextLine().trim();
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        out.println(message);
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcomeMessage() {
        showMessage(MESSAGE_WELCOME);
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodByeMessage() {
        showMessage(MESSAGE_GOODBYE);
    }

    /**
     * Displays a divider line.
     */
    public void showLine() {
        showMessage(DIVIDER);
    }

    /**
     * Displays the current number of tasks in the list.
     *
     * @param taskCount The number of tasks.
     */
    public void showCount(int taskCount) {
        showMessage("Now you have " + taskCount + (taskCount > 1 ? " tasks" : " task") + " in the list.");
    }

    /**
     * Displays a message after a task is added.
     *
     * @param taskCount The updated number of tasks.
     * @param task      The task that was added.
     */
    public void showAddMessage(int taskCount, Task task) {
        showMessage(MESSAGE_ADD + LS + task.toString());
        showCount(taskCount);
    }

    /**
     * Displays a message after a task is deleted.
     *
     * @param taskCount The updated number of tasks.
     * @param task      The task that was deleted.
     */
    public void showDeleteMessage(int taskCount, Task task) {
        showMessage(MESSAGE_DELETE + LS + task.toString());
        showCount(taskCount);
    }


}
