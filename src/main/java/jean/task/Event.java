package jean.task;

/**
 * Represents a Task that happens at a specific point in time.
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructs an Event object.
     *
     * @param description The description of the task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        super.numberOfTasks += 1;
    }

    /**
     * Returns the String in the format to be saved.
     *
     * @return The formatted String to be saved.
     */
    public String getSaveData() {
        return "E|" + (super.isDone ? 1 : 0) + "|" + super.description + "|" + this.at;
    }

    /**
     * Returns the String representation of the Event object.
     *
     * @return The formatted String to be displayed.
     */
    @Override
    public String toString() {
        return ("[E]" + super.toString() + "(at: " + this.at + ")");
    }
}
