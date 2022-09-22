package ado.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a deadline task with description and date to be completed by.
 */
public class Deadline extends Task {

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
        this.formattedDate = date.format(DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }

    @Override
    public String toStringForStorage() {
        return "D|" + super.toStringForStorage() + "|" + date.toString();
    }
}
