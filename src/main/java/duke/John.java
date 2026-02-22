package duke;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class John {

    private static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void addTask(Task newTask) throws IOException {
        tasks.add(newTask);
        System.out.println("Got it. I've added this task: " + System.lineSeparator() + newTask.toString());
        System.out.println("Now you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.");
        Save();
    }

    private static final String FILE_PATH = java.nio.file.Paths.get("data", "john.txt").toString();

    public static void Save() throws IOException {
        try {
            File f = new File(FILE_PATH);
            if (f.getParentFile() != null && !f.getParentFile().exists()) {
                f.getParentFile().mkdir();
            }
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                String isDone = task.getIsDone() ? "1" : "0";
                fw.write(task.toSaveFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void Load() throws IOException {
        File f = new File(FILE_PATH);
        if (!f.exists()) {
            return;
        }
        try (Scanner s = new Scanner(f)) {
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] parts = line.split("\\|");
                String type = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String description = parts[2].trim();
                Task newTask = null;
                switch (type) {
                case "T":
                    newTask = new Todo(description);
                    break;
                case "D":
                    newTask = new Deadline(description, parts[3].trim());
                    break;
                case "E":
                    String[] duration = parts[3].split("-");
                    newTask = new Event(description, duration[0].trim(), duration[1].trim());
                    break;
                }

                if (newTask != null) {
                    if (isDone) {
                        newTask.markAsDone();
                    }
                    tasks.add(newTask);
                }
            }
        }
    }

    public static void main(String[] args) throws DukeException, IOException {
        String name = "John";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        Load();
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
                    for (int i = 0; i < tasks.size(); i++) {
                        int num = i + 1;
                        System.out.println(num + "." + tasks.get(i).toString());
                    }
                } else if (words[0].equals("mark")) {
                    if (words.length < 2) {
                        throw new DukeException("Please specify which task you want to mark.");
                    }
                    int item = Integer.parseInt(words[1]) - 1;
                    if (item > tasks.size() - 1) {
                        throw new DukeException("This task does not exist.");
                    }
                    tasks.get(item).markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(tasks.get(item));
                } else if (words[0].equals("delete")) {
                    if (words.length < 2) {
                        throw new DukeException("Please specify which task you want to delete.");
                    }
                    int item = Integer.parseInt(words[1]) - 1;
                    if (item > tasks.size() - 1) {
                        throw new DukeException("This task does not exist.");
                    }
                    System.out.println("Noted. I've removed this task:" + System.lineSeparator() + tasks.get(item));
                    tasks.remove(item);
                    System.out.println("Now you have " + tasks.size() + " in the list.");
                    Save();
                } else if (words[0].equals("unmark")) {
                    if (words.length < 2) {
                        throw new DukeException("Please specify which task you want to unmark.");
                    }
                    int item = Integer.parseInt(words[1]) - 1;
                    if (item > tasks.size() - 1) {
                        throw new DukeException("This task does not exist.");
                    }
                    tasks.get(item).markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet: ");
                    System.out.println(tasks.get(item));
                    Save();
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
                    String description = line.substring(firstSpace + 1, byIndex - 1);
                    String by = line.substring(byIndex + 1).replace("by ", "");
                    addTask(new Deadline(description, by));
                } else if (words[0].equals("event")) {
                    int firstSpace = line.indexOf(" ");
                    if (!line.contains("/from") || (!line.contains("/to"))) {
                        throw new DukeException("Please enter event in the correct format (with /from and /to).");
                    }
                    int fromIndex = line.indexOf("/from");
                    int toIndex = line.indexOf("/to");
                    String description = line.substring(firstSpace + 1, fromIndex - 1);
                    String startTime = line.substring(fromIndex + 1, toIndex - 1).replace("from ", "");
                    String endTime = line.substring(toIndex + 1).replace("to ", "");
                    addTask(new Event(description, startTime, endTime));
                } else {
                    throw new DukeException("OOPS! Sorry I don't understand this :(");
                }
            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
