package ploopy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that has a date as well as
 * a name.
 */
public class Event extends Task {
    /** Input string to Date formatter */
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy kkmm");
    /** Date to output String formatter */
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy kkmm");
    /** Date of task (If applicable) */
    private final LocalDateTime dateTime;
    /** String format of date */
    private final String dateStringForm;

    /**
     * {@inheritDoc}
     */
    public Event(String name, String date) {
        super(name);
        type = "E";
        dateStringForm = date;
        this.dateTime = LocalDateTime.parse(date, INPUT_FORMATTER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%s %s%s", super.toString(), getDate(), getPriorityForString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDate() {
        return String.format("(at: %s)", OUTPUT_FORMATTER.format(dateTime));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDateForFileWrite() {
        return dateStringForm;
    }
}
