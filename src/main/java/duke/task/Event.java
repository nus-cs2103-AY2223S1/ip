package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event which is one type of task.
 */
public class Event extends Task {
    private LocalDate eventDate;

    /**
     * Constructs a new Event instance with a description and a date.
     *
     * @param description Description of the event.
     * @param eventDate Date of the event.
     */
    public Event(String description, LocalDate eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    /**
     * Checks if an event has a matching date.
     *
     * @param localDate Given date.
     * @return True if the event has the given date; false otherwise.
     */
    @Override
    public boolean matchDate(LocalDate localDate) {
        return eventDate.equals(localDate);
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + eventDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns if a given object is equal to an Event instance.
     *
     * @param object Given object.
     * @return True if the two are equal, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Event) {
            Event event = (Event) object;
            if (this == event) {
                return true;
            } else {
                boolean isSameDescription = this.description.equals(event.description);
                boolean isSameDate = this.eventDate.equals(event.eventDate);
                return isSameDescription && isSameDate;
            }
        } else {
            return false;
        }
    }

}
