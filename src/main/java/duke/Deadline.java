package duke;

import java.time.LocalDate;

/**
 * Represents a <code>Task</code> with deadline.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Construct a <code>Deadline</code> <code>Task</code>.
     *
     * @param description description of task.
     * @param deadline deadline of task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
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
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Returns string representaion of the <code>Deadline</code>.
     *
     * @return string representaion of the <code>Deadline</code>.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " ( by: " + deadline + " )";
    }

    /**
     * Returns a string formatted to be read by a <code>Storage</code> object.
     *
     * @return formatted string.
     */
    @Override
    public String toStorageFormat() {
        return "D | " + super.toStorageFormat() + " | " + deadline;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Deadline) {
            Deadline otherTask = (Deadline) obj;
            if (otherTask.description == this.description && this.deadline == otherTask.deadline) {
                return true;
            }
        }
        return false;
    }
}
