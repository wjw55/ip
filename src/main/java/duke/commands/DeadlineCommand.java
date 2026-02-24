package duke.commands;

import java.io.IOException;
import java.time.LocalDateTime;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.task.Deadline;
import duke.task.Task;

public class DeadlineCommand extends Command {
    private String description;
    private LocalDateTime by;

    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = new Deadline(description, by);
        tasks.addTask(task);
        storage.Save(tasks);
        ui.showAddMessage(tasks.size(), task);
    }

}
