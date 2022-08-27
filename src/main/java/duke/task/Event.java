package duke.task;

public class Event extends Task {
    /**
     * Child class of Task with a date and time
     */
    private static final String SYMBOL = "[E]";
    protected String dateTime;

    /**
     * Constructor for the Event class
     *
     * @param description description of the event
     * @param dateTime date and time of the event
     */
    public Event(String description, String dateTime) {
        super(description, SYMBOL);
        this.dateTime = dateTime;
    }

    /**
     * Method to get the date of the event
     *
     * @return the date of the event
     */
    public String getDate() {
        return this.dateTime;
    }

    /**
     * Method to get the info of the event
     * in the format of [E]--[ ]--{event name}--{date}
     *
     * @return the info of the event
     */
    @Override
    public String getInfo() {
        return (super.getInfo() + "--"
                + getDate());
    }

    /**
     * Method to get the string info of the event
     * in the format of [E][ ] {event name} (at: {date})
     *
     * @return the date of the event
     */

    /**
     * Method to return a string representation of the event
     * @return string representation of the event
     */
    @Override
    public String toString() {
        return SYMBOL + super.toString()
                + " (at: " + dateTime + ")";
    }
}

