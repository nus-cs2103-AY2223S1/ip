package duke;

import java.time.LocalDate;

/**
 * Represents a <code>Task</code> with deadline.
 */
public class Deadline extends Task {
    private final LocalDate DEADLINE;

    /**
     * Construct a <code>Deadline</code> <code>Task</code>.
     *
     * @param description description of task.
     * @param deadline deadline of task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.DEADLINE = LocalDate.parse(deadline);
    }

    /**
     * Construct a <code>Deadline</code> <code>Task</code>.
     *
     * @param description description of task.
     * @param isDone state of task.
     * @param deadline deadline of task.
     */
    public Deadline(String description, boolean isDone , String deadline) {
        super(description, isDone);
        this.DEADLINE = LocalDate.parse(deadline);
    }

    /**
     * Returns string representation of the <code>Deadline</code>.
     *
     * @return string representation of the <code>Deadline</code>.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " ( by: " + DEADLINE + " )";
    }

    /**
     * Returns a string formatted to be read by a <code>Storage</code> object.
     *
     * @return formatted string.
     */
    @Override
    public String toStorageFormat() {
        return "D | " + super.toStorageFormat() + " | " + DEADLINE;
    }


}
