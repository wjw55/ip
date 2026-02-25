package duke.commands;

import static duke.common.Messages.MESSAGE_LIST;

import java.io.IOException;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that displays all tasks
 * currently stored in the task list.
 *
 * This command does not modify the task list
 * or storage; it only prints existing tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
     *
     * Displays a header message followed by all tasks
     * in the task list with their corresponding index numbers.
     *
     * @param tasks   The task list to be displayed.
     * @param ui      The user interface used to display messages.
     * @param storage The storage handler (not modified).
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showMessage(MESSAGE_LIST);
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            ui.showMessage(num + "." + tasks.get(i).toString());
        }
    }
}
