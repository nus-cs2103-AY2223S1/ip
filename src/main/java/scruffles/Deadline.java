package scruffles;
import java.time.LocalDate;

/**
 * A task that is due at a specific deadline, which is represented by a date
 *
 * @author Shamus Tan
 */
public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String taskName, LocalDate by) {
        super(taskName);
        this.by = by;
    }

    public Deadline(String taskName, LocalDate by, boolean isDone) {
        super(taskName);
        this.by = by;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String date = by.getDayOfMonth() + " " + by.getMonth().toString() + " " + by.getYear();
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}