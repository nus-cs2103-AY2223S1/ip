package task;
import java.time.LocalDateTime;

/**
 * Represents a deadline task
 */
public class Event extends TimeTask {

    private final static String ICON = "E";

    /**
     * Instantiates a new Event task
     */
    public Event(String description, LocalDateTime time) {
        super(description, ICON, time);
    }

    @Override
    public String toString() {
        return String.format("[E]" + "[%s] " + super.toString() + " (at: " + super.getDate() + ")", super.getStatusIcon());
    }

    /**
     * Returns the string format for saving into a file
     *
     * @return String format for saving into a file
     */
    public String toSave() {
        return String.format("[E]" + "[%s] " + super.toString() + " (at: " + super.getDateSave() + ")", super.getStatusIcon());
    }
}
