package duke;

/**
 * Represents a type of task. An Event object corresponds to a task with a specific start and end time
 * e.g. lesson at 2-4pm.
 */
public class Event extends Task {
    protected String time;

    /**
     * Creates new Event object.
     *
     * @param description Description of task.
     * @param isDone Completion status of task.
     * @param time Start and end time of task.
     */
    public Event(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * Returns String in format for writing into file.
     *
     * @return storage string.
     */
    @Override
    public String getStorageString() {
        String result = "E | " + (this.isDone ? "1 | " : "0 | ") + this.description + " | " + this.time;
        if (this.getTag() != null) {
            result += " | " + this.getTag();
        }
        return result;
    }

    /**
     * Returns String representation of Event object.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        String result = "[E]" + super.toString() + " (at: " + this.time + ")";
        if (this.getTag() != null) {
            result += " #" + this.getTag();
        }
        return result;
    }
}
