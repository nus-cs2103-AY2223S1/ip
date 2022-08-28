package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.parser.Parser;

/**
 * Represents a task that needs to be done before a specific date.
 */
public class Deadline extends Task {
    private static final String TASK_SYMBOL = "D";
    private static final String DATE_OUTPUT_FORMAT = "d MMM yyyy";
    private String by;

    /**
     * Constructor for a Deadline.
     * @param description The description of the task.
     * @param by The date that the task needs to be done before.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the String representation of the Deadline task.
     * @return a String indicating the Deadline task's symbol, status, description and date.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", Deadline.TASK_SYMBOL, super.toString(), getFormattedDate());
    }

    /**
     * Returns the type of the Deadline task.
     * @return "D".
     */
    @Override
    public String getType() {
        return Deadline.TASK_SYMBOL;
    }

    /**
     * Returns the date of the Deadline task.
     * @return the date of the Deadline task.
     */
    @Override
    public String getDate() {
        return by;
    }

    private String getFormattedDate() {
        LocalDate date = Parser.parseDate(by);
        return date.format(DateTimeFormatter.ofPattern(DATE_OUTPUT_FORMAT));
    }
}
