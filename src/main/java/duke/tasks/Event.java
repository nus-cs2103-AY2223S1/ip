package duke.tasks;

import java.time.LocalDateTime;

import duke.tools.Parser;

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
     * Updates the event with a new datetime.
     *
     * @param newDateTime New datetime to be updated.
     */
    public void updateDateTime(LocalDateTime newDateTime) {
        dateAndTime = newDateTime;
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
        String eventDataFormat = "E | %s | %s | %s\n";
        return String.format(eventDataFormat, isDone, super.description,
                Parser.formatDateTimeToData(dateAndTime));
    }

    /**
     * Returns string representation of event task.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        String eventStringFormat = "[E]%s (at: %s)";
        return String.format(eventStringFormat, super.toString(),
                Parser.formatDateTimeToPrint(dateAndTime));
    }
}
