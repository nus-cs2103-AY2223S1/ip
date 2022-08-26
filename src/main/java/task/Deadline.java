package task;

import java.time.LocalDateTime;

/**
 * Represents a deadline, a type of task
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructs a deadline with some description and a datetime to indicate
     * the deadline
     *
     * @param description The specified description.
     * @param isDone      The boolean indicating whether the task is done.
     * @param deadline    The specified datetime for the deadline.
     */
    public Deadline(String description, boolean isDone, LocalDateTime deadline) {
        super(description, isDone);
        this.deadline = deadline;
        this.taskType = TaskType.D;
    }

    public String getDeadline() {
        return Task.dateTimeDisplayFormatter.format(this.deadline);
    }

    /**
     * Deadline has an additional datetime field for deadline
     * <p>
     * {@inheritDoc}
     */
    @Override
    public String encode(String delimiter) {
        return super.encode(delimiter) + delimiter + this.deadline.format(Task.dateTimeParser);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.getDeadline() + ")";
    }
}
