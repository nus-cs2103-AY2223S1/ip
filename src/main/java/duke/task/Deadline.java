package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the user's task, specifically a deadline to be completed before a specific date.
 */
public class Deadline extends Task {
    /** The date of the deadline. */
    protected LocalDate endDate;

    public Deadline(String description, LocalDate endDate) {
        super(description);
        this.endDate = endDate;
    }

    public Deadline(boolean done, String description, LocalDate endDate) {
        super(done, description);
        this.endDate = endDate;
    }

    @Override
    public String getTask() {
        String done = this.isDone ? "1" : "0";
        return String.format("D | %s | %s | %s", done, this.description, this.endDate);
    }

    @Override
    public String toString() {
        String formattedDate = this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
