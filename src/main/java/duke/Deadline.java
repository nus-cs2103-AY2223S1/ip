package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate by;

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
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
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
