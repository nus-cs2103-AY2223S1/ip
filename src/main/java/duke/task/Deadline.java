package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with description and date to be completed by.
 */
public class Deadline extends Task {

    protected String by;
    private LocalDate date;
    private String formattedDate;

    /**
     * Creates a deadline task object.
     * @param description details of task.
     * @param date date of when task needs to be completed.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
        this.formattedDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + formattedDate + ")";
    }

    @Override
    public String toStringForStorage() {
        return "D|" + super.toStringForStorage() + "|" + date.toString();
    }
}
