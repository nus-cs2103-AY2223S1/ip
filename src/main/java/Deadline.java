package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    /**
     * Deadline of 'Deadline' object
     */
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Override 'toString' method to return status and description of
     * 'Deadline' object.
     * @return [D][COMPLETION STATUS][TASK DESCRIPTION]
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String date = by.format(formatter);
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
