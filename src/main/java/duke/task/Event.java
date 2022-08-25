package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task
 *
 * @author benjytan45678
 * @version 0.1
 */
public class Event extends Task {
    private String time;
    private String name;

    private String date;

    /**
     * Instantiates an event with specified name, date and time.
     *
     * @param name name of the event task
     * @param date date of the event task
     * @param time time of the event task
     */
    public Event(String name, LocalDate date, LocalTime time) {
        super(name);
        this.date = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        this.time = DateTimeFormatter.ofPattern("hh:mm a").format(time);
        this.name = name;
    }

    /**
     * Instantiates a event with specified name, date and time.
     *
     * @param name name of the event task
     * @param date date of the event task
     * @param time time of the event task
     */
    public Event(String name, String date, String time) {
        super(name);
        this.time = time;
        this.date = date;
    }

    /**
     * Creates a String representation of the event task
     *
     */
    @Override
    public String toString() {

        return "[E]" + super.toString() + " (at: " + this.date + " " + this.time + ")";
    }

    /**
     * Creates a simplified version of the event task to be stored in local file
     *
     */
    public String parse() {
        if (this.getHasCompleted()) {
            return "E#1#" + this.name + "#" + this.date + "#" + this.time;
        } else {
            return "E#0#" + this.name + "#" + this.date + "#" + this.time;
        }

    }
}
