package duke.tasklist;

import java.io.IOException;
import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents a collection of Task objects.
 *
 * This class manages all tasks currently stored in memory.
 * It provides methods to add, remove, retrieve, and query tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a new task to the task list.
     *
     * @param newTask The task to be added.
     */
    public void add(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param i The index of the task.
     * @return The task at the given index.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Removes the task at the specified index.
     *
     * @param item The index of the task to remove.
     */
    public void remove(int item) {
        tasks.remove(item);
    }

    /**
     * Returns the underlying list of tasks.
     *
     * @return The ArrayList containing all tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
