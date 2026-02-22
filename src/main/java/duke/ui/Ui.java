package duke.ui;

import java.io.InputStream;
import java.util.Scanner;

import duke.task.Task;

public class Ui {
    private final Scanner in;
    private static final String DIVIDER = "===================================================";

    public Ui() {
        this(System.in);
    }

    public Ui(InputStream in) {
        this.in = new Scanner(in);
    }

    public void showWelcome() {
        String name = "John";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
    }

    public void showGoodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public String readCommand() {
        return in.nextLine().trim();
    }

    public void showAutoMessage(int taskCount, Task task) {
        showMessage("Got it. I've added this task: " + System.lineSeparator() + task.toString());
        showMessage("Now you have " + taskCount + (taskCount > 1 ? " tasks" : " task") + " in the list.");
    }

}
