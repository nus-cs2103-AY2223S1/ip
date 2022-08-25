package duke.models;

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

    @Override
    public String getSymbol() {
        return "D";
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String stringToWrite() {
        return this.getSymbol() + " | " + (super.isDone ? "1" : "0") + " | " + this.getDescription() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
