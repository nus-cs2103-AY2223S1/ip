package duke;

/**
 * Task with a period of time of the task
 */
public class Event extends Task{
    /**
     * Event object at field which indicates the timings
     */
    protected String at;

    /**
     * A constructor to initialize the Event object with the description and timings
     *
     * @param description The task
     * @param at The timings
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns a string that represents the Event
     * @return String A string that represents the current object
     */
    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns a string that represents the Event in text file format
     * @return String A string that represents the current object
     */
    @Override
    public String toFileString() {
        return "E | " +  this.getFileStatus() + " | " + super.toString() + " | " + this.at;
    }
}
