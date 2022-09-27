package unc.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import unc.UncException;

/**
 * A type of Task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor for new Deadlines.
     *
     * @param description Description of task.
     * @param by The date/time to complete task by.
     * @throws UncException If the date/time can't be parsed correctly.
     */
    public Deadline(String description, String by) throws UncException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException exception) {
            throw new UncException("Invalid datetime");
        }
    }

    /**
     * Constructor for saved Deadlines.
     *
     * @param description Description of task.
     * @param by The date/time to complete task by.
     * @param done Whether the task was saved as done.
     * @throws UncException If the date/time can't be parsed correctly.
     */
    public Deadline(String description, String by, String done) throws UncException {
        super(description, done.equals("true"));
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException exception) {
            throw new UncException("Invalid datetime");
        }
    }

    @Override
    public LocalDate getDateTime() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toStorageString() {
        return "D" + "///" + this.description + "///" + this.by + "///" + this.isDone;
    }
}
