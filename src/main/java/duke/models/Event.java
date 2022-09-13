package duke.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A child class Event that inherits properties description and isDone from Task
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Initialises an Event object with description and date of the event
     * @param description A short description of the event
     * @param at The date where this event is happening
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the string representation of the {@code Event} object
     * @return
     */
    @Override
    public String getSymbol() {
        return "E";
    }

    /**
     * Returns the description of the {@code Event} object
     * @return description as a string
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the string representation of the {@code Event} object
     * in a format that is convenient to save and load files
     * @return String to write to file
     */
    @Override
    public String stringToWrite() {
        return this.getSymbol() + "|" + (super.isDone ? "1" : "0") + "|" + this.getDescription() + "|" + this.at;
    }

    /**
     * Postpone the {@code Task} object
     */
    public void postponeTask() {
        this.at = this.at.plusDays(1);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
