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

public class Ui {
    private final Scanner in;
    private final PrintStream out;
    private static final String DIVIDER = "===================================================";

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String readCommand() {
        return in.nextLine().trim();
    }

    public void showMessage(String message) {
        out.println(message);
    }

    public void showWelcomeMessage() {
        showMessage(MESSAGE_WELCOME);
    }

    public void showGoodByeMessage() {
        showMessage(MESSAGE_GOODBYE);
    }

    public void showLine() {
        showMessage(DIVIDER);
    }

    public void showCount(int taskCount) {
        showMessage("Now you have " + taskCount + (taskCount > 1 ? " tasks" : " task") + " in the list.");
    }

    public void showAddMessage(int taskCount, Task task) {
        showMessage(MESSAGE_ADD + LS + task.toString());
        showCount(taskCount);
    }

    public void showDeleteMessage(int taskCount, Task task) {
        showMessage(MESSAGE_DELETE + LS + task.toString());
        showCount(taskCount);
    }


}
