package bob;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents Event object, a task with a specific occurrence date
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Constructor for Event object with description and occurrence date
     *
     * @param description name or details of event
     * @param at date of event
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the save format of the Event object
     *
     * @return String representing how Event object is saved
     */
    @Override
    public String toSave() { return "E | " + super.toSave() + "| " + at; }

    /**
     * Returns the string representation of the Event object
     *
     * @return String representation of Event object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
