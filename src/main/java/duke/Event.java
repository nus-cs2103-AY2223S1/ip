package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Contains additional information relevant to an Event task.
 * 
 * @author Siau Wee
 */
public class Event extends Task {

    private final DateTimeFormatter FORMAT_MMDDYY_TIME = 
            DateTimeFormatter.ofPattern("MM/dd/yy, HH:mm");
            
    private LocalDateTime endTime;

    /**
     * Constructor to initialise an Event object with given
     * taskname and end time.
     * 
     * @param taskName The name of the Event to be created.
     * @param endTime The time of the Event
     */
    public Event(String taskName, LocalDateTime endTime) {
        super(taskName);
        this.endTime = endTime;
    }

    /**
     * Returns the string representation of the Event object.
     * 
     * @return String with task's name, and event's date and time
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + 
                this.endTime.format(FORMAT_MMDDYY_TIME) + ")";
    }
}
