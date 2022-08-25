import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class creates event
 * @author Shaune Ang
 */
public class Event extends Task{
    LocalDate time;

    /**
     * Event constructor for task creation by user
     * @param name name of task
     * @param time deadline of task
     */
    Event(String name, String time) {
        super(name);
        this.time = LocalDate.parse(time);
    }

    /**
     * Event constructor for task loaded from saved file
     * @param name name of task
     * @param time deadline of task
     * @param status completed status of task
     */
    Event(String name, String time, boolean status) {
        super(name, status);
        this.time = LocalDate.parse(time);
    }

    /**
     * String format for displaying event task
     * @return string format for displaying event task
     */
    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", super.toString(), time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    /**
     * Returns deadline of the Deadline object
     * @return deadline to string in YYYY-MM-DD format
     */
    public String getTime() {
        return time.toString();
    }
}
