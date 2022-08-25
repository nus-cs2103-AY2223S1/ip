package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Event class represents a task that
 * happens at a specified time.
 */
public class Event extends Task {
    private String at;
    private LocalDate date;

    /**
     * Constructs a new Event task with a specified
     * description and time at which it occurs.
     *
     * @param description A string specifying the description of the event.
     * @param at A string specifying the time at which the event occurs.
     */
    public Event(String description, String at) {
        super(description);
        try {
            LocalDateTime dateTime;
            dateTime = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("yyyy-M-d HHmm"));
            date = dateTime.toLocalDate();
            this.at = dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h.mma"));
        } catch (DateTimeParseException e1) {
            try {
                String[] strings = at.split(" ");
                date = LocalDate.parse(strings[0]);
                this.at = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                if (strings.length > 1) {
                    this.at += " " + at.substring(at.indexOf(" ") + 1);
                }
            } catch (DateTimeParseException e2) {
                this.at = at;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns the representation of an Event when stored in a data file on the hard disk.
     *
     * @return a string representing the Event as it is stored on a data file on the hard disk.
     */
    @Override
    public String toData() {
        return "E | " + super.toData() + " | " + at + "\n";
    }

    /**
     * Checks if the date at which an Event occurs is the specified date.
     *
     * @param date The specified date to check.
     * @return true if the specified date is the date at which the Event occurs.
     */
    @Override
    public boolean onDate(LocalDate date) {
        return date.equals(this.date);
    }
}
