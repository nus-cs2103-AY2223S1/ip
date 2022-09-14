package ploopy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task that has a date as well as
 * a name.
 */
public class Deadline extends Task {
    /** Input string to Date formatter */
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy kkmm");
    /** Date to output String formatter */
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy kkmm");
    /** Date of task (If applicable) */
    private LocalDateTime dateTime;
    /** String format of date */
    private String dateStringForm;
    /**
     * {@inheritDoc}
     */
    public Deadline(String name, String dueDate) {
        super(name);
        type = "D";
        dateStringForm = dueDate;
        this.dateTime = LocalDateTime.parse(dueDate, INPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return String.format("%s %s%s", super.toString(), getDate(), getPriorityForString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDate() {
        return String.format("(by: %s)", OUTPUT_FORMATTER.format(dateTime));
    }

    @Override
    public String getDateForFileWrite() {
        return dateStringForm;
    }
}
