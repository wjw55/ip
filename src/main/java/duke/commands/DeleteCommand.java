package duke.commands;

import static duke.common.Messages.MESSAGE_TASK_DOES_NOT_EXIST;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Task;
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
            throw new DukeException(MESSAGE_TASK_DOES_NOT_EXIST);
        }
        Task task = tasks.get(item);
        tasks.remove(item);
        ui.showDeleteMessage(tasks.size(), task);
        storage.Save(tasks);
    }
}
