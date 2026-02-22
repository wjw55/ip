package duke.commands;

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
            throw new DukeException("This task does not exist.");
        }
        tasks.get(item).markAsUndone();
        ui.showMessage("OK, I've marked this task as not done yet: " + System.lineSeparator() + tasks.get(item));
        storage.Save(tasks);
    }
}
