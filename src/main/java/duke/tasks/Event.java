package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class encapsulates an event.
 */
public class Event extends Task{
    private LocalDateTime time;

    public Event(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    /**
     * Returns string representation of the type and isDone attributes of an Event object.
     *
     * @return String representation of the status of an Event object.
     */
    @Override
    public String getStatus() {
        if(this.isDone) {
            return "[E][X]";
        } else {
            return "[E][ ]";
        }
    }

    /**
     * Returns string representation of an Event object.
     *
     * @return String representation of an Event object.
     */
    @Override
    public String toString() {
        return this.getStatus() + " " + this.description + " (at: " +
                this.time.format(DateTimeFormatter.ofPattern("hh:mm a 'on' dd/MM/yyyy")) + ")";
    }

    /**
     * Returns true if object passed is of type Event and has the same description as the Event object.
     *
     * @param obj Object to be compared with.
     * @return Whether object passed and the Event object are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event e = (Event) obj;
            return this.description.equals(e.description) && this.time.equals(e.time);
        }
        return false;
    }
}
