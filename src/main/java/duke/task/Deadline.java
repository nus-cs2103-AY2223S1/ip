package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task
 *
 * @author benjytan45678
 * @version 0.1
 */
public class Deadline extends Task {
    private String date;
    private String time;
    private String name;

    /**
     * Instantiates a deadline with specified name, date and time.
     *
     * @param name name of the deadline task
     * @param date date of the deadline task is due
     * @param time time of the deadline task is due
     */
    public Deadline(String name, LocalDate date, LocalTime time) {
        super(name);
        this.date = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        this.time = DateTimeFormatter.ofPattern("hh:mm a").format(time);
        this.name = name;
    }

    /**
     * Instantiates a deadline with specified name, date and time.
     *
     * @param name name of the deadline task
     * @param date date of the deadline task is due
     * @param time time of the deadline task is due
     */
    public Deadline(String name, String date, String time) {
        super(name);
        this.time = time;
        this.date = date;
    }

    /**
     * Creates a simplified version of the deadline task to be stored in local file
     *
     */
    public String parse() {
        if (this.getHasCompleted()) {
            return "D#1#" + this.name + "#" + this.date + "#" + this.time;
        } else {
            return "D#0#" + this.name + "#" + this.date + "#" + this.time;
        }

    }

    /**
     * Creates a String representation of the deadline task
     *
     */
    @Override
    public String toString() {

        return "[D]" + super.toString() + " (by: " + this.date + " " + this.time + ")";
    }
}
