package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.parser.Parser;

/**
 * Represents a task that occurs on a specific date.
 */
public class Event extends Task {
    private static final String TASK_SYMBOL = "E";
    private static final String DATE_OUTPUT_FORMAT = "d MMM yyyy";
    private String at;

    /**
     * Constructor for an Event.
     * @param description The description of the task.
     * @param at The date that the task occurs on.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the String representation of the Event task.
     * @return a String indicating the Event task's symbol, status, description and date.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", Event.TASK_SYMBOL, super.toString(), getFormattedDate());
    }

    /**
     * Returns the type of the Event task.
     * @return "E".
     */
    @Override
    public String getType() {
        return Event.TASK_SYMBOL;
    }

    /**
     * Returns the date of the Event task.
     * @return the date of the Event task.
     */
    @Override
    public String getDate() {
        return at;
    }

    private String getFormattedDate() {
        LocalDate date = Parser.parseDate(at);
        return date.format(DateTimeFormatter.ofPattern(DATE_OUTPUT_FORMAT));
    }
}
