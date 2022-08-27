package cheese.task;

import java.time.LocalDateTime;

import cheese.utils.DateTimeUtils;

public class Deadline extends Task {
    /** Datetime of deadline */
    private LocalDateTime deadline;

    /**
     * Constructor to create a new Deadline
     * 
     * @param description description of Deadline
     * @param deadline deadline of Deadline
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = DateTimeUtils.parseString(deadline);
    }

    public Deadline(boolean isDone, String description, String deadline) {
        super(isDone, description);
        this.deadline = DateTimeUtils.parseString(deadline);
    }

    @Override
    public String toFileString() {
        String formattedDeadline = DateTimeUtils.parseLocalDateTimeToInput(deadline);
        return "deadline // " + super.toFileString() + " // " + formattedDeadline;
    }

    /**
     * Returns string representation of Deadline
     * 
     * @return string representation of Deadline
     */
    @Override
    public String toString() {
        String formattedDeadline = DateTimeUtils.parseLocalDateTimeToOutput(deadline);
        return "[D]" + super.toString() + " (by: " + formattedDeadline + ")";
    }
}
