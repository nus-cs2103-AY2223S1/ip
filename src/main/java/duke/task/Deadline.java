package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Task of type deadline.
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDateTime byDate;

    /**
     * Constructor of a deadline task.
     *
     * @param description description of task
     * @param by string representing deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor of a deadline task.
     *
     * @param description description of task
     * @param byDate date and time representing deadline
     */
    public Deadline(String description, LocalDateTime byDate) {
        super(description);
        this.byDate = byDate;
    }

    public String getOutput() {
        return String.format("D | %d | %s | %s | %s", getIsDone() ? 1 : 0, getDescription(), by, this.tag);
    }

    @Override
    public String toString() {
        if (byDate != null) {
            return "[D]" + super.toString()
                    + " (by: " + byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")" + this.tag;
        } else {
            return "[D]" + super.toString() + " (by: " + by + ") # " + this.tag;
        }
    }
}
