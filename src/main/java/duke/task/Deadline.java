package duke.task;

import java.time.LocalDateTime;

/**
 * Represents a deadline in Duke.
 */
public class Deadline extends Task {
    protected LocalDateTime deadlineTiming;

    /**
     * Creates a new instance of deadline.
     *
     * @param description The description of a deadline.
     * @param deadlineTiming The datetime of when a deadline is due.
     */
    public Deadline(String description, LocalDateTime deadlineTiming) {
        super(description);
        assert !description.isEmpty() : "Description of a deadline should not be empty";

        this.deadlineTiming = deadlineTiming;
        this.taskType = TaskType.DEADLINE;
    }

    /**
     * Encodes the deadline into a decodable and readable string representation for storage.
     *
     * @return The encoded string representation of a deadline for storage.
     */
    @Override
    public String encode() {
        return super.encode() + " | " + this.deadlineTiming;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + deadlineTiming.format(Task.DATE_TIME_DISPLAY_FORMATTER) + ")";
    }
}
