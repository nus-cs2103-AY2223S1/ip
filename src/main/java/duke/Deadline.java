package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A class that creates deadline task.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate date;
    protected LocalTime time;

    /**
     * A constructor that initialises the deadline task.
     *
     * @param description Description of the deadline task.
     * @param by Describes the date and time for the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Parses the date and sets it to the object's date
     *
     * @param date Date to parse in
     */
    public void parseDate(String date) {
        this.date = LocalDate.parse(date);
    }

    /**
     * Parses the time and sets it to the object's time
     *
     * @param time Time to parse in
     */
    public void parseTime(String time) {
        this.time = LocalTime.parse(time);
    }

    /**
     * Returns string of the deadline task.
     *
     * @inheritDoc Inherits from toString() method but returns description of deadline task.
     * @return String that describes the deadline task and its deadline.
     */
    @Override
    public String toString() {
        if (by.contains("/") || by.contains("-")) {
            String date = this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            String time = this.time.toString();

            return "[D]" + super.toString() + " (by: " + date + " " + time + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }
}
