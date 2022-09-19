package kkbot.tasks;

import kkbot.parser.Parser;

/**
 * Deadlines are tasks that have a description and must be done after a certain time.
 *
 * @author AkkFiros
 */
public class DoAfter extends Task {
    private static final String DOAFTER_SYMBOL = "A";
    protected String date;

    /**
     * Constructor for DoAfter task
     * @param description description of a task that the user inputs
     * @param date the start time for user to begin the task
     */
    public DoAfter(String description, String date) {
        super(description);
        this.date = date;
    }

    /**
     * toString method for a DoAfter task
     * @return string representation of a DoAfter task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s (by: %s)", DoAfter.DOAFTER_SYMBOL,
                super.toString(), Parser.parseDate(date));
    }

    /**
     * Method to retrieve a DoAfter task's type
     * @return Symbol representation of a DoAfter task
     */
    @Override
    public String getType() {
        return DoAfter.DOAFTER_SYMBOL;
    }

    /**
     * Retrieves the start time of a doafter task
     * @return the start time of a task
     */
    @Override
    public String getDate() {
        return date;
    }
}
