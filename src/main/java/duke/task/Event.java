package duke.task;

public class Event extends Task {
    protected String startTime;
    protected String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description, TaskType.EVENT);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return super.toString() + "(from: " + startTime + " to: " + endTime + ")";
    }

    @Override
    public String toSaveFormat() {
        return super.toSaveFormat() + " | " + this.startTime + "-" + this.endTime;
    }
}
