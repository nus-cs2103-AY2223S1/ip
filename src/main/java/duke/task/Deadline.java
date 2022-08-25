package duke.task;

import java.time.LocalDate;

/**
 * Class to represent tasks of the type duke.task.Deadline.
 *
 * @author Melissa Anastasia Harijanto
 */
public class Deadline extends Task {
    /** String that represents when a task must be done by. */
    protected LocalDate by;

    /**
     * Constructor for the duke.task.Deadline class.
     *
     * @param taskName The name of the task.
     * @param by The time it has to be done by.
     */
    public Deadline(String taskName, LocalDate by) {
        super(taskName);
        this.by = by;
    }

    /**
     * Returns the String representation of the duke.task.Deadline task.
     *
     * @return the String representation of the duke.task.Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
    
}
