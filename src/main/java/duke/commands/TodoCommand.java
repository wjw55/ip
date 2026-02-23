package duke.commands;

import java.io.IOException;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.task.Task;
import duke.task.Todo;

public class TodoCommand extends Command {
    private String descripton;

    public TodoCommand(String descripton) {
        this.descripton = descripton;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = new Todo(descripton);
        tasks.addTask(task);
        storage.Save(tasks);
        ui.showAddMessage(tasks.size(), task);
    }
}
