package duke.commands;

import static duke.common.Messages.MESSAGE_FIND;

import java.io.IOException;
import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command{
    private String item;

    public FindCommand(String item) {
        this.item = item;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ArrayList<Task> taskArrayList = tasks.getTasks();
        ui.showMessage(MESSAGE_FIND);
        for (Task task : taskArrayList) {
            String description = task.getDescription();
            if (description.contains(item)){
                ui.showMessage(task.toString());
            }
        }
    }
}
