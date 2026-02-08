import java.util.Scanner;

public class John {
    private static Task[] Tasks = new Task[100];
    private static int taskCount = 0;

    public static void addTask(Task t) {
        Tasks[taskCount] = t;
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
                        System.out.println(num + "." + Tasks[i].toString());
                    }
                } else if (words[0].equals("mark")) {
                    int item = Integer.parseInt(words[1]) - 1;
                    Tasks[item].markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(Tasks[item]);
                } else if (words[0].equals("unmark")) {
                    int item = Integer.parseInt(words[1]) - 1;
                    Tasks[item].markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet: ");
                    System.out.println(Tasks[item]);
                } else if (words[0].equals("todo")) {
                    if (words.length < 2) {
                        throw new DukeException("OOPS! Seems like you did not enter a description for todo ;-;");
                    }
                    int firstSpace = line.indexOf(" ");
                    String description = line.substring(firstSpace + 1);
                    addTask(new Todo(description));
                } else if (words[0].equals("deadline")) {
                    int firstSpace = line.indexOf(" ");
                    int byIndex = line.indexOf("/by ");
                    String description = line.substring(firstSpace + 1, byIndex);
                    String by = line.substring(byIndex + 1).replace("by ", "");
                    addTask(new Deadline(description, by));
                } else if (words[0].equals("event")) {
                    int firstSpace = line.indexOf(" ");
                    int fromIndex = line.indexOf("/from");
                    int toIndex = line.indexOf("/to");
                    String description = line.substring(firstSpace + 1, fromIndex);
                    String startTime = line.substring(fromIndex + 1, toIndex).replace("from ", "");
                    String endTime = line.substring(toIndex + 1).replace("to ", "");
                    addTask(new Event(description, startTime, endTime));
                } else {
                    throw new DukeException("OOPS! I'm sorry but I dont understand this :( Please enter an valid task");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
