package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A class that creates the Event task.
 */
public class Event extends Task{
    protected String at;
    protected LocalDate date;
    protected LocalTime time;

    /**
     * A constructor for the Event task.
     *
     * @param description Describes the activity of the Event task.
     * @param at Date and time of the Event task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
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
     * Returns the description of the Event task.
     *
     * @return String that describes the activity, date and time of the Event task.
     */
    @Override
    public String toString() {
        if (this.at.contains("/") || at.contains("-")) {
            String date = this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            String time = this.time.toString();

            return "[E]" + super.toString() + " (at: " + date + " " + time + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
    }
}
