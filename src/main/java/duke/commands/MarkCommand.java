package duke.commands;

import static duke.common.Messages.MESSAGE_MARK;
import static duke.common.Messages.MESSAGE_TASK_DOES_NOT_EXIST;

import java.io.IOException;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;

/**
 * Represents the command that marks a task that is done
 *
 * When executed, it marks the specific task
 * and uses a message to signal the user that the task is marked.
 */
public class MarkCommand extends Command{
    private int item;

    /**
     * Public construction of the class
     * @param item
     */
    public MarkCommand(int item) {
        this.item = item;
    }


    /**
     * Mark the specific task and display message to notify user
     * @param tasks   The task list containing all current tasks.
     * @param ui      The user interface used to display messages.
     * @param storage The storage handler used for saving tasks.
     * @throws IOException If an I/O error occurs.
     * @throws DukeException If a command-related error occurs.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        if (item > tasks.size() - 1 || item <= 0) {
            throw new DukeException(MESSAGE_TASK_DOES_NOT_EXIST);
        }
        tasks.get(item).markAsDone();
        ui.showMessage(MESSAGE_MARK + System.lineSeparator() + tasks.get(item));
        storage.Save(tasks);
    }
}
