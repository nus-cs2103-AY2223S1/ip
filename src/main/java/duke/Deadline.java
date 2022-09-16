package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline, which is a Task with a deadline.
 */
public class Deadline extends Task {
    protected final LocalDate deadline;

    /**
     * Constructor of Deadline with description and deadline.
     *
     * @param description Description of the Task.
     * @param deadline Deadline of the Task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
        type = 'D';
    }

    /**
     * Constructor of Deadline with description, boolean to set the Deadline as done or not done, and deadline.
     *
     * @param description Description of the Task.
     * @param isDone Boolean to set the Task as done or not done.
     * @param deadline Deadline of the Task.
     */
    public Deadline(String description, boolean isDone, String deadline) {
        this(description, deadline);
        this.isDone = isDone;
    }

    /**
     * Returns the String representation of the Deadline for UI.
     *
     * @return String representation of the Deadline for UI.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns the String representation of the Deadline for Storage.
     *
     * @return String representation of the Deadline for Storage.
     */
    @Override
    public String toData() {
        return super.toData() + ", " + deadline;
    }
}
