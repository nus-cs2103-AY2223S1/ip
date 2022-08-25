import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class creates deadlines
 * @author Shaune Ang
 */
public class Deadline extends Task{
    LocalDate deadline;

    /**
     * Deadline constructor for task creation by user
     * @param name name of task
     * @param deadline deadline of task
     */
    Deadline(String name, String deadline) {
        super(name);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Deadline constructor for task loaded from saved file
     * @param name name of task
     * @param deadline deadline of task
     * @param status completed status of task
     */
    Deadline(String name, String deadline, boolean status) {
        super(name, status);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * String format for displaying deadline task
     * @return string format for displaying deadline task
     */
    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    /**
     * Returns deadline of the Deadline object
     * @return deadline to string in YYYY-MM-DD format
     */
    public String getDeadline() {
        return deadline.toString();
    }
}
