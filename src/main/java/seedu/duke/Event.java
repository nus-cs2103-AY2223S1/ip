package seedu.duke;

/**
 * Represents an Event task.
 */
public class Event extends Task {

    protected String dueTime;

    /**
     * Creates an Event task.
     * @param description description of Event.
     * @param dueTime Due time of Event.
     */
    public Event(String description, String dueTime) {
        super(description);
        this.dueTime = dueTime;
    }

    /**
     * Returns String representation of dueTime attribute.
     * @return dueTime attribute.
     */
    public String getDueTime(){
        return this.dueTime;
    }

    @Override
    public String toString() {
        String output = String.format("[E][%s] %s (at: %s)", this.getStatusIcon(), this.description, this.dueTime);
        return output;
    }
}

