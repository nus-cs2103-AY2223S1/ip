package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Contains additional information relevant to an Event task.
 * 
 * @author Siau Wee
 */
public class Event extends Task implements Comparable<Task> {

    private static final DateTimeFormatter FORMAT_MMDDYY_TIME =
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

    public LocalDateTime getEventTime() {
        return this.eventTime;
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

    @Override
    public int compareTo(Task otherTask) {
        if (otherTask instanceof Todo) {
            return -1;
        } else {
            if (otherTask instanceof Deadline) {
                @SuppressWarnings("unchecked")
                Deadline deadlineTask = (Deadline) otherTask;
                return this.eventTime.compareTo(deadlineTask.getDeadLine());
            } else if (otherTask instanceof Event) {
                @SuppressWarnings("unchecked")
                Event eventTask = (Event) otherTask;
                return this.eventTime.compareTo(eventTask.eventTime);
            } else {
                return 0;
            }
        }
    }
}
