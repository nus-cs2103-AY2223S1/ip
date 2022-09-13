package duke.task;
import java.time.LocalDateTime;

/**
 * Represents a deadline task it is a task that has
 * deadline
 */
public class Deadline extends TimeTask {

    private final static String ICON = "D";

    /**
     * Instantiates a new deadline task
     */
    public Deadline(String description, LocalDateTime time) {
        super(description, ICON, time);
    }

    /**
     * Returns the string format of a Deadline task
     *
     * @return Returns String format of a Deadline task
     */
    @Override
    public String toString() {
        return String.format("[D]" + "[%s] " + super.toString()
                + " (by: " + super.getDate() + ")", super.getStatusIcon());
    }

    /**
     * Returns the string format of a Deadline task
     * that is used for saving into a file
     *
     * @return Returns String format of a Deadline task
     * used for saving into a file
     */
    public String toSave() {
        return String.format("[D]" + "[%s] " + super.toString()
                + " (by: " + super.getDateSave() + ")", super.getStatusIcon());
    }
}
