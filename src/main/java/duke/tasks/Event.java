package duke.tasks;

/**
 * Represents an event in the task list
 */
public class Event extends Task {
    protected String time;

    /**
     * Constructor Method of Event class
     * @param description
     * @param time
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Constructor Method of Event class
     * @param isDone
     * @param description
     * @param time
     */
    public Event(boolean isDone, String description, String time) {
        super(isDone, description);
        this.time = time;
    }

    /**
     * Converts Event Object to String
     * @return String representation of Event Object
     */
    @Override
    public String toString() {
        String s = super.toString();
        return "[E]" + s + " (at: " + time + ")";
    }

    /**
     * Converts Event Object to a String to be stored in a file
     * @return condensed String representation of Event object
     */
    public String toFile() {
        String s = super.toFile();
        return "E," + s + "," + time;
    }
}
