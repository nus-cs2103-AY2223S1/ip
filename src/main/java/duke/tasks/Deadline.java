package duke.tasks;

import duke.parser.DateParser;

/**
 * Represents a task that needs to be done before a specific date.
 */
public class Deadline extends Task {
    private static final String TASK_SYMBOL = "D";
    private String date;

    /**
     * Constructor for a Deadline task.
     * @param description The description of the task.
     * @param date The date that the task needs to be done before.
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns the String representation of the Deadline task.
     * @return A String indicating the Deadline task's symbol, status, description and date.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", Deadline.TASK_SYMBOL, super.toString(), DateParser.getParsedDate(date));
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
     * @return The date of the Deadline task.
     */
    @Override
    public String getDate() {
        return date;
    }

    /**
     * Checks if the Deadline task occurs on a specific date.
     * @param date The date to check if the Deadline task occurs on.
     * @return True if the Deadline task occurs on the given date.
     */
    @Override
    public boolean isOnDate(String date) {
        return this.date.equals(date);
    }
}
