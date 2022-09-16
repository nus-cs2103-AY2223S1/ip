package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A deadline task with a description and a deadline.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Creates a new deadline task with a description and a deadline.
     * @param description the description of the task
     * @param by the deadline of the task
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! The date of a deadline must be in the format of yyyy-mm-dd.");
        }
    }

    @Override
    public String fileFormat() {
        return String.format("D | %d | %s | %s | %s", isDone ? 1 : 0,
        priority == null ? "0" : priority, description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")"
                + (priority == null ? "" : " - " + priority);
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline deadline = (Deadline) obj;
            return this.description.equals(deadline.description) && this.by.equals(deadline.by);
        }
        return false;
    }
}
