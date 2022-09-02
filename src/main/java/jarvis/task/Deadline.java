package jarvis.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline --- deadline task.
 */
public class Deadline extends Task {
    private final LocalDate by;

    /**
     * Constructor.
     *
     * @param description description of task.
     * @param by deadline of task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("" + "[dd/MM/yyyy]" + "[MMM dd yyyy]"));
    }

    /**
     * Gets the deadline of the task.
     *
     * @return deadline of task.
     */
    public String getBy() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
