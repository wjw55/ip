package duke.task;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Represents a Deadline task in Duke.
 *
 * A Deadline task has a description, a completion status,
 * and a due date/time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with the given description and due date/time.
     *
     * @param description The task description.
     * @param by          The due date/time of the task.
     */
    public Deadline(String description, LocalDateTime by){
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    /**
     * Returns the deadline date/time.
     *
     * @return The LocalDateTime the task is due by.
     */

    public LocalDateTime getDateTime() {
        return this.by;
    }

    /**
     * Returns a string representation of the task for display.
     *
     * Format example: [D][X] Submit report (by: Feb 25 2026 14:00)
     *
     * @return Formatted string for display.
     */
    @Override
    public String toString(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return super.toString() + " (by: " + this.by.format(dateTimeFormatter) + ")";
    }

    /**
     * Returns a string representation suitable for saving to file.
     *
     * Format example: D | 1 | Submit report | 2026-02-25T14:00
     *
     * @return Formatted string for storage.
     */
    @Override
    public String toSaveFormat() {
        return super.toSaveFormat() + " | " + this.by;
    }
}
