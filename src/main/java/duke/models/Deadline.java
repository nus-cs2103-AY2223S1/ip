package duke.models;

import duke.Postponable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A child class Deadline that inherits properties description and isDone from Task
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Initialises an Deadline object with description and date of the deadline
     * @param description
     * @param by
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the {@code Deadline} object
     * @return "D"
     */
    @Override
    public String getSymbol() {
        return "D";
    }

    /**
     * Returns the description of the {@code Deadline} object
     * @return
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the string representation of the {@code Deadline} object
     * in a format that is convenient to save and load files
     * @return String to write to file
     */
    @Override
    public String stringToWrite() {
        return this.getSymbol() + " | " + (super.isDone ? "1" : "0") + " | " + this.getDescription() + " | " + this.by;
    }

    @Override
    public void postponeTask() {
        this.by = this.by.plusDays(1);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
