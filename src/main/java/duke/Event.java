package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

/**
 * A type of task that stores a date, start and end time
 *
 * @author Sean Lam
 */
public class Event extends Task {
    protected String type = "[E]";
    protected LocalDate date;
    protected LocalTime from;
    protected LocalTime to;

    /**
     * Constructor for Event class
     *
     * @param description Description of task
     * @param date Date of deadline
     * @param from Starting time of deadline
     * @param to Starting time of deadline
     */
    Event(String description, String date, String from, String to) {
        super(description);
        this.date = LocalDate.parse(date);
        this.from = LocalTime.parse(from);
        this.to = LocalTime.parse(to);
    }

    /**
     * Constructor for Event class
     *
     * @param description Description of task
     * @param date Date of deadline
     * @param from Starting time of deadline
     * @param to Starting time of deadline
     */
    Event(String description, LocalDate date, LocalTime from, LocalTime to) {
        super(description);
        this.date = date;
        this.from = from;
        this.to = to;
    }

    /**
     * Returns string representation of Event task for display in TaskList
     *
     * @return Event task string
     */
    @Override
    public String toString() {
        String strDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String strFrom = from.format(DateTimeFormatter.ofPattern("hhmma"));
        String strTo = to.format(DateTimeFormatter.ofPattern("hhmma"));
        return type + super.toString() + " (at: " + strDate + ", " + strFrom + " to " + strTo + ")";
    }

    /**
     * Returns string representation of Event task to be stored in dukeHistory
     *
     * @return Event task string
     */
    @Override
    public String toFile() {
        String isDone = "0";
        if (super.isDone) {
            isDone = "1";
        }
        return String.format("E|%s|%s|%s|%s|%s\n", isDone, super.description, date, from, to);
    }
}
