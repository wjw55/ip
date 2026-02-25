package duke.commands;

import static duke.common.Messages.MESSAGE_FIND;

import java.io.IOException;
import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that searches for tasks containing
 * a specified keyword in their description.
 *
 * When executed, this command displays all matching tasks
 * to the user without modifying the task list or storage.
 */
public class FindCommand extends Command{
    private String item;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param item The keyword used to search task descriptions.
     */
    public FindCommand(String item) {
        this.item = item;
    }

    /**
     * Executes the find command.
     *
     * Iterates through all tasks in the task list and displays
     * those whose descriptions contain the specified keyword.
     *
     * @param tasks   The task list to search within.
     * @param ui      The user interface used to display messages.
     * @param storage The storage handler (not modified).
     * @throws IOException If an I/O error occurs.
     */
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
