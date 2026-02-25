package duke.commands;

import java.io.IOException;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a command that adds a new Todo task
 * to the task list.
 *
 * A Todo task contains only a description and
 * has no associated date or time.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Constructs a TodoCommand with the specified description.
     *
     * @param description The description of the todo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the todo command.
     *
     * Creates a new Todo task using the stored description,
     * adds it to the task list, saves the updated list to storage,
     * and displays a confirmation message to the user.
     *
     * @param tasks   The task list to which the todo will be added.
     * @param ui      The user interface used to display messages.
     * @param storage The storage handler used to persist task data.
     * @throws IOException If an error occurs while saving to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = new Todo(description);
        tasks.add(task);
        storage.Save(tasks);
        ui.showAddMessage(tasks.size(), task);
    }
}
