package duke;

import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class John {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void addTask(Task t) {
        tasks[taskCount] = t;
        taskCount++;
        System.out.println("Got it. I've added this task: " + System.lineSeparator() + t.toString());
        System.out.println("Now you have " + taskCount + (taskCount > 1 ? " tasks" : " task") + " in the list.");
    }

    public static void main(String[] args) throws DukeException {
        String name = "John";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        String line;
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            try {
                line = in.nextLine();
                if (line.equals("bye")) {
                    break;
                }
                String[] words = line.split(" ");
                if (line.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        int num = i + 1;
                        System.out.println(num + "." + tasks[i].toString());
                    }
                } else if (words[0].equals("mark")) {
                    if (words.length < 2) {
                        throw new DukeException("Please specify which task you want to mark.");
                    }
                    int item = Integer.parseInt(words[1]) - 1;
                    if (item > taskCount - 1) {
                        throw new DukeException("This task does not exist.");
                    }
                    tasks[item].markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(tasks[item]);
                } else if (words[0].equals("unmark")) {
                    if (words.length < 2) {
                        throw new DukeException("Please specify which task you want to unmark.");
                    }
                    int item = Integer.parseInt(words[1]) - 1;
                    if (item > taskCount - 1) {
                        throw new DukeException("This task does not exist.");
                    }
                    tasks[item].markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet: ");
                    System.out.println(tasks[item]);
                } else if (words[0].equals("todo")) {
                    if (words.length < 2) {
                        throw new DukeException("OOPS! Seems like you did not enter a description for todo ;-; Please enter a todo with description");
                    }
                    int firstSpace = line.indexOf(" ");
                    String description = line.substring(firstSpace + 1);
                    addTask(new Todo(description));
                } else if (words[0].equals("deadline")) {
                    int firstSpace = line.indexOf(" ");
                    if (!line.contains("/by ")) {
                        throw new DukeException("Please enter deadline in the correct format (with /by).");
                    }
                    int byIndex = line.indexOf("/by ");
                    String description = line.substring(firstSpace + 1, byIndex);
                    String by = line.substring(byIndex + 1).replace("by ", "");
                    addTask(new Deadline(description, by));
                } else if (words[0].equals("event")) {
                    int firstSpace = line.indexOf(" ");
                    if (!line.contains("/from") || (!line.contains("/to"))) {
                        throw new DukeException("Please enter event in the correct format (with /from and /to).");
                    }
                    int fromIndex = line.indexOf("/from");
                    int toIndex = line.indexOf("/to");
                    String description = line.substring(firstSpace + 1, fromIndex);
                    String startTime = line.substring(fromIndex + 1, toIndex).replace("from ", "");
                    String endTime = line.substring(toIndex + 1).replace("to ", "");
                    addTask(new Event(description, startTime, endTime));
                } else {
                    throw new DukeException("OOPS! Sorry I don't understand this :(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
