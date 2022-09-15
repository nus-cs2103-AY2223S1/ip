package duke;

/**
 * Represents an Event Task.
 * @author Tan Wen Cong
 */
public class Event extends Task {
    private String time;

    /**
     * Constructor for Event
     *
     * @param description Description of the Event
     * @param time        Time of Event
     * @param isDone      boolean indicating if the Event is done
     */
    public Event(String description, String time, boolean isDone) {
        super(description, "E", isDone);
        this.time = time;
    }

    /**
     * Returns String representation of the Event object
     *
     * @return String representation of the Event object
     */
    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), this.time);
    }

    /**
     * Return String representation of Event to be saved in Txt file
     *
     * @return String representation of Event to be saved in Txt file
     */
    @Override
    public String getTxtString() {
        return String.format("event %s /at %s", super.getTxtString(), time);
    }
}
