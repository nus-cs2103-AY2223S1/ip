package duke;

/**
 * Represents an event with its description,
 * completion status and its date.
 *
 * @author  Gerald Teo Jin Wei
 * @version 0.1
 * @since   2022-08-28
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructor for Event class
     * @param description Description of event
     * @param date Date of event
     * @param isDone Completion status of event
     */
    public Event(String description, String date, boolean isDone) {
        super(description, isDone);
        this.at = date;
    }

    /**
     * Constructor for Event class without
     * specifying completion status
     * @param description Description of event
     * @param date Date of event
     */
    public Event(String description, String date) {
        super(description);
        this.at = date;
    }

    /**
     * Returns the task's type, completion status, description and date
     * @return String This returns the string of the event in the specified format
     */
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.description + " (at: " + at + ")";
    }
}
