package duke.task;

/**
 * Represents a task in Duke.
 *
 * This is the base class for all tasks (Todo, Deadline, Event),
 * storing common attributes like description, completion status, and type.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    /**
     * Constructs a Task with a description and type.
     * By default, the task is not done.
     *
     * @param description The task description.
     * @param type        The type of task (Todo, Deadline, Event).
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Returns the task description.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether the task is completed.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the label of the task type.
     *
     * @return A string representing the task type.
     */
    public String getTaskType() {
        return this.type.getLabel();
    }

    /**
     * Returns the status icon representing completion.
     *
     * @return "X" if done, " " if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone()  {
        this.isDone = true;
    }

    /**
     * Makrs the task as not done.
     */
    public void markAsUndone()  {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task for display.
     *
     * @return Formatted string including type, status, and description.
     */
    public String toString() {
        return "[" + getTaskType() + "]" + "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a string suitable for saving to file.
     *
     * Format: TYPE | isDone(1/0) | description
     *
     * @return The task serialized for storage.
     */
    public String toSaveFormat() {
        return this.getTaskType() + " | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }
}

