package duke.task;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by){
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    public LocalDateTime getDateTime() {
        return this.by;
    }

    @Override
    public String toString(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return super.toString() + " (by: " + this.by.format(dateTimeFormatter) + ")";
    }

    @Override
    public String toSaveFormat() {
        return super.toSaveFormat() + " | " + this.by;
    }
}
