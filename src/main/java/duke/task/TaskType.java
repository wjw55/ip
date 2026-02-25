package duke.task;

/**
 * Enum representing the type of task in Duke.
 *
 * Each type has a label used for saving to file and displaying in the UI:
 *TODO → "T"
 *DEADLINE → "D"
 *EVENT → "E"
 */
public enum TaskType {
    TODO("T"), DEADLINE("D"), EVENT("E");

    private final String label;

    /**
     * Constructs a TaskType with the given label.
     *
     * @param label The string label for the task type.
     */
    TaskType(String label) {
        this.label = label;
    }

    /**
     * Returns the label of the task type.
     *
     * @return The label as a String.
     */
    public String getLabel() {
        return label;
    }
}
