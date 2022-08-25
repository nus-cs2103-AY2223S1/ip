package duke;

/**
 * Represents an Event at a specified time
 */
public class Event extends Task {
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getTime() {
        return at;
    }

    /**
     * Generates an encoding of the Task for use in storage
     * @return encoded string following the storage format
     */
    public String getStorageString() {
        return "E" + "|" + (isDone ? "1" : "0") + "|" + getDescription() + "|" + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
