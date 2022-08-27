package roofus.task;

import java.time.LocalDate;

/**
 * Event is a type of Task that contains a description,
 * boolean value that indicates if it is completed and
 * a LocalDate attribute that represents the time the event
 * occurs.
 */
public class Event extends Task {
    private LocalDate start;

    /**
     * Constructs an instance of Event.
     *
     * @param description A description of the Event instance.
     * @param start The time of Event.
     */
    public Event(String description, String start) {
        super(description);
        this.start = LocalDate.parse(start);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String writeString() {
        return String.format("E | %d | %s | %s",
                super.isDone ? 1 : 0, super.description, start);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[E]%s at: %s",
                super.toString(), start.toString());
    }
}
