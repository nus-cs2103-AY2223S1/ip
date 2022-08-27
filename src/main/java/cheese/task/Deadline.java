package cheese.task;

import java.time.LocalDateTime;
import cheese.utils.DateTimeUtils;

/**
 * Represents a deadline with description, complete/incomplete status, and deadline.
 */
public class Deadline extends Task {
    /** Datetime of deadline */
    private LocalDateTime deadline;

    /**
     * Constructs an instance of <code>Deadline</code>.
     * 
     * @param description Description of deadline.
     * @param deadline Deadline of deadline.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = DateTimeUtils.parseString(deadline);
    }

    /**
     * Constructs an instance of <code>Deadline</code>.
     * 
     * @param isDone Whether deadline is complete or incomplete.
     * @param description Description of deadline.
     * @param deadline Deadline of deadline.
     */
    public Deadline(boolean isDone, String description, String deadline) {
        super(isDone, description);
        this.deadline = DateTimeUtils.parseString(deadline);
    }

    /**
     * Returns string representation of deadline to save in file (eg. deadline // T // Do homework
     * // 2022-12-07 12:00).
     * 
     * @return String representation of deadline to save in file.
     */
    @Override
    public String toFileString() {
        String formattedDeadline = DateTimeUtils.parseLocalDateTimeToInput(deadline);
        return "deadline // " + super.toFileString() + " // " + formattedDeadline;
    }

    /**
     * Returns string representation of deadline.
     * 
     * @return string representation of deadline.
     */
    @Override
    public String toString() {
        String formattedDeadline = DateTimeUtils.parseLocalDateTimeToOutput(deadline);
        return "[D]" + super.toString() + " (by: " + formattedDeadline + ")";
    }
}
