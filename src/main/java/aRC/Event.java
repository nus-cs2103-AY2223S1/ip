package aRC;

/**
 * Encapsulates an Event task
 */
public class Event extends Task {

    private String time;

    /**
     * Constructor for aRC.Event
     * @param title The title of aRC.Event
     * @param isDone The isDone status of the aRC.Event
     * @param time The time of aRC.Event
     */
    public Event(String title, boolean isDone, String time) {
        super(title, isDone);
        this.time = time;
    }

    /**
     * Returns how an Event should be represented
     * @return String representation of Event
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.time);
    }

    /**
     * Returns how an Event should be stored in a txt file
     * @return String representation of Event
     */
    @Override
    public String fileFormat() {
        return String.format("E|%d|%s|%s", this.isDone ? 1 : 0, this.title, this.time);
    }
}
