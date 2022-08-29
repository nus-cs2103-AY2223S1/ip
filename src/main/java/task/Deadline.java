package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline is a type of task that has a deadline for task and
 * a description of the deadline.
 */
public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate date) {
        super(description);
        by = date;
    }
    public Deadline(String description, String dateString, boolean isDone) {
        super(description, isDone);
        by = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String getSaveFormat() {
        return "D" + " | " + (getIsDone() ? 1 : 0) + " | " + getDescription() + " | " +
                by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
