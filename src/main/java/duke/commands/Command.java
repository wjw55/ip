package duke.commands;

import java.io.IOException;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage)
        throws DukeException, IOException;

    public boolean isExit() {
        return false;
    }
}
