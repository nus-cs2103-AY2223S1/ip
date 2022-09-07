package doke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represent the Events task. Extends the Task class.
 */
public class Events extends Task{

    private LocalDate at;

    /**
     * a public constructor for the Events class
     *
     * @param description description for the events task.
     * @param at date of when the event start.
     */
    public Events(String description, String at) {
        super(description);
        LocalDate date = LocalDate.parse(at);
        this.at = date;
    }

    /**
     * Returns a LocalDate object representing the time of the task.
     *
     * @return a LocalDate time of the task.
     */
    @Override
    public LocalDate getTime() {
        return at;
    }

    /**
     * Returns a String representing the type of Event task
     *
     * @return String "E"
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Returns a string representation of the Events object.
     *
     * @return a string representation of the Events object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")) + ")";
    }
}
