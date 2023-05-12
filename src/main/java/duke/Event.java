package duke;

/**
 * Event Class to represent a class that will store Event Objects
 * @author Ashiqur Rahman A02030107Y
 */
public class Event extends Task {
    protected String eventTime;

    /**
     * Constructor for Event class
     * @param description Details of Task
     * @param eventTime Time of event
     */
    public Event (String description, String eventTime) {
        super(description);
        if (eventTime.substring(0,3).equals("at:")) {
            this.eventTime = eventTime;
        } else {
            this.eventTime = eventTime.replaceFirst(" ", "at: ");
        }
    }

    @Override
    public String toString() {
        String s = String.format("[E]%s (%s)", super.toString(), this.eventTime);
        return s;
    }
}
