package unc.task;

import unc.UncException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A type of task that happens at a specific date/time.
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Constructor for new Events.
     *
     * @param description Description of task.
     * @param at The date/time task happens.
     * @throws UncException If the date/time can't be parsed correctly.
     */
    public Event(String description, String at) throws UncException {
        super(description);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException exception) {
            throw new UncException("Invalid datetime");
        }
    }

    /**
     * Constructor for saved Events.
     *
     * @param description Description of task.
     * @param at The date/time task happens.
     * @param done Whether the task was saved as done.
     * @throws UncException If the date/time can't be parsed correctly.
     */
    public Event(String description, String at, String done) throws UncException {
        super(description, done == "true");
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException exception) {
            throw new UncException("Invalid datetime");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ")";
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String toStorageString() {
        return "E" + "///" + this.description + "///" + this.at + "///" + this.isDone;
    }
}