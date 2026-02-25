package duke.commands;

import java.io.IOException;
import java.time.LocalDateTime;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.task.Deadline;
import duke.task.Task;


/**
 * Represents a command that adds a new Deadline task to the task list.
 *
 * A Deadline task contains a description and a specific due date/time.
 * When executed, this command creates the Deadline, adds it to the task list,
 * saves the updated list to storage, and displays a confirmation message.
 */
public class DeadlineCommand extends Command {
    private String description;
    private LocalDateTime by;

    /**
     * Constructs a DeadlineCommand with the specified description and due time.
     *
     * @param description The description of the deadline task.
     * @param by          The due date and time of the task.
     */
    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the deadline command.
     *
     * Creates a new Deadline task using the stored description and due time,
     * adds it to the task list, saves the updated task list to storage,
     * and displays a confirmation message to the user.
     *
     * @param tasks   The task list to which the deadline will be added.
     * @param ui      The user interface used to display messages.
     * @param storage The storage handler used to persist task data.
     * @throws IOException If an error occurs while saving to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = new Deadline(description, by);
        tasks.add(task);
        storage.Save(tasks);
        ui.showAddMessage(tasks.size(), task);
    }

}
