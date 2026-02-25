package duke.commands;

import java.io.IOException;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.task.Event;
import duke.task.Task;

/**
 * Represents a command that adds a new Event task to the task list.
 *
 * An Event task contains a description, a start time, and an end time.
 * When executed, this command creates the Event, adds it to the task list,
 * saves the updated list to storage, and displays a confirmation message.
 */
public class EventCommand extends Command{
    private String description;
    private String from;
    private String to;

    /**
     * Constructs an EventCommand with the specified details.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the event command.
     *
     * Creates a new Event task using the stored description and time range,
     * adds it to the task list, saves the updated task list to storage,
     * and shows a confirmation message to the user.
     *
     * @param tasks   The task list to which the event will be added.
     * @param ui      The user interface used to display messages.
     * @param storage The storage handler used to persist task data.
     * @throws IOException If an error occurs while saving to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = new Event(description, from, to);
        tasks.add(task);
        storage.Save(tasks);
        ui.showAddMessage(tasks.size(), task);
    }

}
