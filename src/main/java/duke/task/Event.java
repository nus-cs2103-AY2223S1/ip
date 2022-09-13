package duke.task;
import java.time.LocalDateTime;

/**
 * Represents an Event task it is a task that shows
 * when the event starts
 */
public class Event extends TimeTask {

    private final static String ICON = "E";

    /**
     * Instantiates a new Event task
     */
    public Event(String description, LocalDateTime time) {
        super(description, ICON, time);
    }

    /**
     * Returns the string format of an Event task
     *
     * @return Returns String format of an Event task
     */
    @Override
    public String toString() {
        return String.format("[E]" + "[%s] " + super.toString()
                + " (at: " + super.getDate() + ")", super.getStatusIcon());
    }

    /**
     * Returns the string format of an event task
     * when saving into a file
     *
     * @return Returns String format of an event task
     * when saving into a file
     */
    public String toSave() {
        return String.format("[E]" + "[%s] " + super.toString()
                + " (at: " + super.getDateSave() + ")", super.getStatusIcon());
    }
}
