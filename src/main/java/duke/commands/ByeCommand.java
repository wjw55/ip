package duke.commands;

import static duke.common.Messages.MESSAGE_GOODBYE;

import java.io.IOException;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showMessage(MESSAGE_GOODBYE);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
