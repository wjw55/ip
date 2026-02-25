package duke.task;

/**
 * Represents a Todo task in Duke.
 *
 * A Todo task has only a description and a completion status.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the given description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

}
