package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents specific task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDate byDate;

    /**
     * Creates an instance of a deadline.
     * @param desc Description of the deadline
     * @param byDate Date of the deadline
     */
    public Deadline(String desc, LocalDate byDate) {
        super(desc);
        this.byDate = byDate;
    }

    public String getDescription() {
        return super.description;
    }

    /**
     * Return string representation of deadline date.
     *
     * @return String representation of date
     */
    public String getDeadlineBy() {
        return this.byDate.toString();
    }

    /**
     * Return string representation of the deadline.
     *
     * @return String representation of deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Return shorthand of deadline task type.
     *
     * @return D for deadline
     */
    @Override
    public String getTaskType() {
        return "D";
    }
}
