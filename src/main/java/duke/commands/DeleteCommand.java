package duke.commands;

import static duke.common.Messages.MESSAGE_TASK_DOES_NOT_EXIST;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;

/**
 * Represents a command that deletes a task from the task list.
 *
 * When executed, this command validates the provided task index,
 * removes the corresponding task from the task list,
 * updates the storage, and displays a confirmation message.
 */
public class DeleteCommand extends Command{
    private int item;

    /**
     * Constructs a DeleteCommand for the specified task index.
     *
     * @param item The index of the task to be deleted.
     */
    public DeleteCommand(int item) {
        this.item = item;
    }

    /**
     * Executes the delete command.
     *
     * Validates whether the task index exists. If valid,
     * removes the task from the task list, saves the updated
     * list to storage, and displays a confirmation message.
     *
     * @param tasks   The task list from which the task will be removed.
     * @param ui      The user interface used to display messages.
     * @param storage The storage handler used to persist task data.
     * @throws DukeException If the task index does not exist.
     * @throws IOException   If an error occurs while saving to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        if (item > tasks.size() - 1) {
            throw new DukeException(MESSAGE_TASK_DOES_NOT_EXIST);
        }
        Task task = tasks.get(item);
        tasks.remove(item);
        ui.showDeleteMessage(tasks.size(), task);
        storage.Save(tasks);
    }
}
