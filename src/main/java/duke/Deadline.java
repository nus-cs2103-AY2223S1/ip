package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a task that is due by the deadline.
 */
public class Deadline extends Task {

    private LocalDate deadlineDate;

    /**
     * Creates a Deadline object.
     * @param description The description of the Deadline object.
     * @param isDone Boolean value of the status of Deadline.
     * @param deadlineDate The deadline of the object.
     */
    public Deadline(String description, boolean isDone, LocalDate deadlineDate, PriorityLevel.Priority level) {
        super(description, isDone, level);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Returns the String representation of the Deadline.
     *
     * @return String representation of the Deadline.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s) (Priority: %s)", super.getStatusIcon(),
                super.toString(), deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                super.getPriorityLevel());
    }

    /**
     * Returns the String representation of the Deadline that is stored in a file.
     * @return String representation of Deadline.
     */
    @Override
    public String toFileString() {
        return String.format("D | %s | %s | %s | %s", super.getFileIcon(),
                super.toString(), this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                super.getPriorityLevel());
    }
}
