package duke.task;

/**
 * Represents an Event type of Task object.
 * An Event type of Task starts and ends at a specific date and time.
 */
public class Event extends Task {

    private static final String EVENT_LETTER = "E";

    
    /** String to store the date and time when the Event is happening */
    protected final String dateAndTime;


    /**
     * Creates a new Event object.
     * 
     * @param description Description of the Event object.
     * @param dateAndTime String containing the date and time in any format.
     */
    public Event(String description, String dateAndTime) {
        this(description, false, dateAndTime);
    }

    private Event(String description, boolean isDone, String dateAndTime) {
        super(description, isDone);
        this.dateAndTime = dateAndTime;
    }
    

    @Override
    public Event markTask() {
        return new Event(description, true, dateAndTime);
    }


    @Override
    public Event unmarkTask() {
        return new Event(description, false, dateAndTime);
    }


    /**
     * Returns the string representation of the Event object.
     * 
     * @return String representation of the Event object.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", EVENT_LETTER, super.toString(), dateAndTime);
    }
}
