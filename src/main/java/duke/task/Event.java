package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * Event is a Task that has a time of occurrence.
 */
public class Event extends Task {


    /**
     * Initializes an Event object.
     *
     * @param description The description of the task.
     * @param date The task's event time.
     */
    public Event(String description, String date) {
        super(description, date);
    }

    /**
     * Returns a string representation of an event.
     *
     * @return Details regarding this event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + super.getDate().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
