package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {


    /**
     * Standard constructor for an event
     * @param description The description of the event
     * @param eventDateTime The date and time of the event occurring
     */
    public Event(String description, String eventDateTime) {
        super(description, eventDateTime);
    }

    /**
     * Overloaded constructor to allow for creation of pre-completed events
     * @param description The description of the event
     * @param isDone Marks the event as having been completed or not
     * @param eventDateTime The event's date and time of occurrence
     */
    public Event(String description, boolean isDone, String eventDateTime) {
        super(description, isDone, eventDateTime);
    }

    @Override
    public String getSaveString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return "EVENT,," + super.getSaveString() + this.getTaskTime().format(formatter);
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String formatted = this.getTaskTime().format(formatter);
        return String.format("[E]%s (on: %s)", super.toString(), formatted);
    }
}
