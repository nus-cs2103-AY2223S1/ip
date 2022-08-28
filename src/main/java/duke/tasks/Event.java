package duke.tasks;

/**
 * Represents events in the task list, storing the description of the event and the time of event as string
 */
public class Event extends Task {

    protected String time;

    /**
     * Constructor of event
     * @param description description of event task
     * @param time time of event
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLetterTag() {
        return "E";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAdditionalInfo() {
        return this.time;
    }

    /**
     * Returns properly formatted string to be displayed to user
     * @return String formatted for user to read
     */
    @Override
    public String toString() {
        return "[" + this.getLetterTag() + "][" + this.getStatusIcon() + "] "
                + this.description + " (at: " + this.time + ")";
    }

}
