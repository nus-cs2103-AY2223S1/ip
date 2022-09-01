package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Contains additional information relevant to an Event task.
 * 
 * @author Siau Wee
 */
public class Event extends Task {

    private static DateTimeFormatter FORMAT_MMDDYY_TIME =
            DateTimeFormatter.ofPattern("MM/dd/yy, HH:mm");
            
    private final LocalDateTime eventTime;

    /**
     * Constructor to initialise an Event object with given
     * taskname and end time.
     * 
     * @param taskName The name of the Event to be created.
     * @param eventTime The time of the Event
     */
    public Event(String taskName, LocalDateTime eventTime) {
        super(taskName);
        this.eventTime = eventTime;
    }

    /**
     * Returns the string representation of the Event object.
     * 
     * @return String with task's name, and event's date and time
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + 
                this.eventTime.format(FORMAT_MMDDYY_TIME) + ")";
    }
}
