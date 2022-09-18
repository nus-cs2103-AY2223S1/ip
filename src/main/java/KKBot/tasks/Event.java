package KKBot.tasks;

import KKBot.parser.Parser;

/**
 * Events are tasks that have a description, a start and end time.
 *
 * @author AkkFiros
 */
public class Event extends Task {
    private static final String EVENT_SYMBOL = "E";
    protected String date;

    /**
     * Constructor for an Event task
     * @param description description of a task that the user inputs
     * @param date the date which this Event task occurs at
     */
    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    /**
     * toString method for an Event task
     * @return string representation of an Event task
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", Event.EVENT_SYMBOL,
                super.toString(), Parser.parseDate(date));
    }

    /**
     * Method to retrieve am Event task's type
     * @return Symbol representation of an Event task
     */
    @Override
    public String getType() {
        return Event.EVENT_SYMBOL;
    }

    /**
     * Retrieves the time that an Event task occurs at
     * @return the time which the Event task occurs at
     */
    public String getDate() {
        return date;
    }

}
