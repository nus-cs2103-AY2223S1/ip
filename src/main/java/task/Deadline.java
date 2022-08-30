package task;
import java.time.LocalDateTime;

/**
 * Represents a deadline task
 */
public class Deadline extends TimeTask {

    private final static String ICON = "D";

    /**
     * Instantiates a new deadline task
     */
    public Deadline(String description, LocalDateTime time) {
        super(description, ICON, time);
    }

    @Override
    public String toString() {
        return String.format("[D]" + "[%s] " + super.toString()
                + " (by: " + super.getDate() + ")", super.getStatusIcon());
    }

    /**
     * Returns the string format for saving into a file
     *
     * @return String format for saving into a file
     */
    public String toSave() {
        return String.format("[D]" + "[%s] " + super.toString()
                + " (by: " + super.getDateSave() + ")", super.getStatusIcon());
    }
}
