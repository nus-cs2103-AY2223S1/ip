package scruffles;

import java.time.LocalDate;

/**
 * A task that is due at a specific deadline, which is represented by a date
 *
 * @author Shamus Tan
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor for Deadline
     *
     * @param taskName the name of the Deadline task
     * @param by the date the Deadline task is due
     */
    public Deadline(String taskName, LocalDate by) {
        super(taskName);
        this.by = by;
    }

    /**
     * Constructor for Deadline
     *
     * @param taskName the name of the Deadline task
     * @param by the date the Deadline task is due
     * @param isDone whether the task has been done
     */
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
