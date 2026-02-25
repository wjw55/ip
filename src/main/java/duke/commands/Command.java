package duke.commands;

import java.io.IOException;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;

/**
 * Represents an abstract user command in the John application.
 *
 * All specific commands must extend this class and implement the execute() method.
 *
 * Each command defines its own behavior when executed.
 */
public abstract class Command {

    /**
     * Executes the command using the given task list, UI, and storage.
     *
     * @param tasks   The task list containing all current tasks.
     * @param ui      The user interface used to display messages.
     * @param storage The storage handler used for saving tasks.
     * @throws DukeException If a command-related error occurs.
     * @throws IOException   If a file I/O error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage)
        throws DukeException, IOException;

    /**
     * Returns whether this command signals the application to exit.
     *
     * By default, commands do not exit the program.
     * Subclasses (e.g., ByeCommand) may override this.
     *
     * @return true if the application should terminate, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
