package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * An Event object is a Task object that has a date by which the event
 * will occur.
 *
 */
public class Event extends Task {

    protected LocalDate at;
    /**
     * Creates a new Event object with a given description and its date.
     *
     * @param description the description of the task
     * @param at the date by which the event occurs
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    /**
     * The task type for a Deadline object is "E".
     *
     * @return "E"
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Returns the String representation of the Event object.
     *
     * @return String representation of the Event object
     */
    @Override
    public String toString() {
        String formattedDate = at.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[E]" + super.toString() + " (at: " + formattedDate + ")";
    }

    /**
     * Formats the details of the Event object such that the information can be saved and loaded
     * from files.
     *
     * @return The String representation of the Event object in a format that can be
     * saved to files.
     */
    @Override
    String saveStringToFile() {
        return String.format("%s%s\n", super.saveStringToFile(), at);
    }
}
