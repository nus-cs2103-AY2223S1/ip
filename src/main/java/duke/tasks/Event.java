package duke.tasks;

import duke.tools.Parser;

import java.time.LocalDateTime;

/**
 * Represents an event task.
 */
public class Event extends Task {
    /** Date and time of event */
    private LocalDateTime dateAndTime;

    /**
     * Constructs an event with specified description and date time.
     *
     * @param description Description of event.
     * @param dateAndTime Date and time of event.
     */
    public Event(String description, Boolean isDone, LocalDateTime dateAndTime) {
        super(description);
        if (isDone) {
            super.markAsDone();
        }
        this.dateAndTime = dateAndTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String taskToDataString() {
        String isDone;
        if (super.isDone) {
            isDone = "O";
        } else {
            isDone = "X";
        }
        return String.format("E | %s | %s | %s\n", isDone, super.description,
                Parser.formatDateTimeToData(dateAndTime));
    }

    /**
     * Returns string representation of event task.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                Parser.formatDateTimeToPrint(dateAndTime));
    }
}
