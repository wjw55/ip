package duke.commands;

import java.io.IOException;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.task.Event;
import duke.task.Task;

public class EventCommand extends Command{
    private String description;
    private String from;
    private String to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = new Event(description, from, to);
        tasks.addTask(task);
        storage.Save(tasks);
        ui.showAutoMessage(tasks.size(), task);
    }

}
