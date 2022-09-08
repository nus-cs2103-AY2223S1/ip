package doke;

import java.time.LocalDate;

/**
 * Represent the Events task. Extends the Task class.
 *
 * @author Stevan Gerard Gunawan
 */
public class Event extends Task {

    private LocalDate at;
    private String dateCache = "";

    /**
     * a public constructor for the Events class
     *
     * @param description description for the events task.
     * @param at date of when the event start.
     */
    public Event(String description, String at) {
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
        if (dateCache.isEmpty()) {
            dateCache = at.format(FORMATTER);
        }
        return "[E]" + super.toString() + " (at: " + dateCache + ")";
    }
}
