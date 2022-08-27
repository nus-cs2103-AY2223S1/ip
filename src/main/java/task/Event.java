package task;

import java.time.LocalDateTime;

import parser.DateTimeParser;

/**
 * <h1>Event class</h1>
 * Task that the user has inputted and is required to do
 * that has a date and time that the Event is occurring at.
 */
public class Event extends Task {
    private LocalDateTime eventDateTime;

    /**
     * Creates an Event object.
     *
     * @param description describes the Task.
     * @param isDone boolean value describing whether the user has done
     *               the Task or not.
     * @param eventDateTime the actual date and time of the Event that
     *                      the Task is to be held at.
     */
    public Event(String description, boolean isDone, LocalDateTime eventDateTime) {
        super(description, isDone);
        this.eventDateTime = eventDateTime;
    }

    /**
     * Returns the String representation of the Event.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateTimeParser.changeDateTimeFormat(eventDateTime) + ")";
    }
}
