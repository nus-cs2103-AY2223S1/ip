package jarvis.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline --- deadline task.
 */
public class Deadline extends Task {
    private final LocalDate dueBy;

    /**
     * Constructor.
     *
     * @param description description of task.
     * @param by deadline of task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.dueBy = LocalDate.parse(by, DateTimeFormatter.ofPattern("" + "[dd/MM/yyyy]" + "[MMM dd yyyy]"));
    }

    /**
     * Gets the deadline of the task.
     *
     * @return deadline of task.
     */
    public String getDueBy() {
        return this.dueBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.dueBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
