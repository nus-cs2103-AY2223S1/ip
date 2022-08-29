package duke.task;

import java.time.LocalDate;

import duke.DateConverter;

/**
 * Deadline, a type of Task.
 */
public class Deadline extends Task {
    private final LocalDate date;

    /**
     * Constructor for Deadline object.
     * @param description Description of deadline.
     * @param by String represents date of deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.date = LocalDate.parse(by);
    }

    @Override
    public String storedString() {
        return "D | " + super.storedString() + " | " + date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + DateConverter.convertToString(date) + ")";
    }
}
