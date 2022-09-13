package duke.tasks;

import duke.parser.DateParser;

/**
 * Represents a task that occurs on a specific date.
 */
public class Event extends Task {
    private static final String TASK_SYMBOL = "E";
    private String date;

    /**
     * Constructor for an Event task.
     * @param description The description of the task.
     * @param date The date that the task occurs on.
     */
    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns the String representation of the Event task.
     * @return A String indicating the Event task's symbol, status, description and date.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", Event.TASK_SYMBOL, super.toString(), DateParser.getParsedDate(date));
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
     * @return The date of the Event task.
     */
    @Override
    public String getDate() {
        return date;
    }

    /**
     * Checks if the Event task occurs on a specific date.
     * @param date The date to check if the Event task occurs on.
     * @return True if the Event task occurs on the given date.
     */
    @Override
    public boolean isOnDate(String date) {
        return this.date.equals(date);
    }
}
