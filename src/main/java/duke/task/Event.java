package duke.task;

/**
 * Represents an Event task in Duke.
 *
 * An Event has a description, completion status, and a start/end time.
 */
public class Event extends Task {
    protected String startTime;
    protected String endTime;

    /**
     * Constructs an Event task with the given description and time range.
     *
     * @param description The task description.
     * @param startTime   The start time of the event.
     * @param endTime     The end time of the event.
     */
    public Event(String description, String startTime, String endTime) {
        super(description, TaskType.EVENT);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a string representation of the task for display.
     *
     * Format example: [E][ ] Meeting (from: 10:00 to: 12:00)
     *
     * @return Formatted string for display.
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }

    /**
     * Returns a string representation suitable for saving to file.
     *
     * Format example: E | 0 | Meeting | 10:00-12:00
     *
     * @return Formatted string for storage.
     */
    @Override
    public String toSaveFormat() {
        return super.toSaveFormat() + " | " + this.startTime + "-" + this.endTime;
    }
}
