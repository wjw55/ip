package duke.commands;

import java.io.IOException;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;

public class DeleteCommand extends Command{
    private int item;

    public DeleteCommand(int item) {
        this.item = item;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        if (item > tasks.size() - 1) {
            throw new DukeException("This task does not exist.");
        }
        ui.showMessage("Noted. I've removed this task:" + System.lineSeparator() + tasks.get(item));
        tasks.remove(item);
        ui.showMessage("Now you have " + tasks.size() + " in the list.");
        storage.Save(tasks);
    }
}
