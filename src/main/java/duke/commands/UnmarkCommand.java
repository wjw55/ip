package duke.commands;

import static duke.common.Messages.MESSAGE_TASK_DOES_NOT_EXIST;
import static duke.common.Messages.MESSAGE_UNMARK;

import java.io.IOException;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;

public class UnmarkCommand extends Command{
    private int item;

    public UnmarkCommand(int item) {
        this.item = item;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        if (item > tasks.size() - 1) {
            throw new DukeException(MESSAGE_TASK_DOES_NOT_EXIST);
        }
        tasks.get(item).markAsUndone();
        ui.showMessage(MESSAGE_UNMARK + System.lineSeparator() + tasks.get(item));
        storage.Save(tasks);
    }
}
