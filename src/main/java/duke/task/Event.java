package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task containing a string description and a date.
 */
public class Event extends Task {
    private LocalDate at;

    /**
     * Initializes an instance of an uncompleted Deadline that has the specified description and due date.
     *
     * @param taskDescription Specified description of the deadline.
     * @param at Date of event.
     */
    public Event(String taskDescription, LocalDate at) {
        super(taskDescription);
        this.at = at;
    }

    /**
     * Initializes an instance of Event that has the specified description, date, and state of completion.
     *
     * @param taskDescription Specified description of the event.
     * @param at Date of event.
     * @param isDone Indicates the state of completion the event.
     */
    public Event(String taskDescription, LocalDate at, Boolean isDone) {
        super(taskDescription);
        this.at = at;
        this.isDoneSetter(isDone);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getTypeIcon() {
        return "[E]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toStorageString() {
        return "E" + "|" + super.toStorageString() + "|" + at;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
