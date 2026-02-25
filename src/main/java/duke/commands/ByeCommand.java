package duke.commands;

import static duke.common.Messages.MESSAGE_GOODBYE;

import java.io.IOException;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents the command that exits the application.
 *
 * When executed, it displays a farewell message
 * and signals the main program loop to terminate.
 */
public class ByeCommand extends Command {

    /**
     * Displays the goodbye message to the user.
     *
     * @param tasks   The current task list (not modified).
     * @param ui      The user interface used to display messages.
     * @param storage The storage handler (not modified).
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showGoodByeMessage();
    }

    /**
     * Indicates that this command should terminate the application.
     *
     * @return true to signal program exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
