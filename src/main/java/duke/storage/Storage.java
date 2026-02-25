package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.tasklist.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Handles loading and saving of tasks to a file.
 *
 * This class is responsible for file I/O operations and
 * converting between in-memory Task objects and their
 * stored string representations.
 */
public class Storage {
    private final String filepath;
    private final Ui ui = new Ui();

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path of the file used to store task data.
     */
    public Storage(String filePath) {
        this.filepath = filePath;
    }

    /**
     * Saves the current task list to the storage file.
     *
     * Each task is written in a specific save format so that
     * it can later be reconstructed when loading.
     *
     * @param tasks The task list to be saved.
     * @throws IOException If an I/O error occurs during writing.
     */
    public void Save(TaskList tasks) throws IOException {
        try {
            File f = new File(this.filepath);
            if (f.getParentFile() != null && !f.getParentFile().exists()) {
                f.getParentFile().mkdir();
            }
            FileWriter fw = new FileWriter(this.filepath);
            ArrayList<Task> taskList = tasks.getTasks();
            for (Task task : taskList) {
                String isDone = task.getIsDone() ? "1" : "0";
                fw.write(task.toSaveFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            ui.showMessage("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file.
     *
     * Reads each line from the file, reconstructs the corresponding
     * Task object based on its stored type, and returns a list of tasks.
     *
     * If the file does not exist, an empty task list is returned.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws IOException If an I/O error occurs during reading.
     */
    public ArrayList<Task> Load() throws IOException {
        File f = new File(this.filepath);
        ArrayList<Task> tasks = new ArrayList<>();
        if (!f.exists()) {
            return tasks;
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
                    LocalDateTime by = LocalDateTime.parse(parts[3].trim());
                    newTask = new Deadline(description, by);
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
        return tasks;
    }
}
