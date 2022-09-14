package duke.tasks;

import java.time.LocalDateTime;

import duke.commons.Parser;

/**
 * Represents an event task.
 */
public class Event extends Task {
    public static final String TASK_ICON = "E";
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
        String eventDataFormat = TASK_ICON + " | %s | %s | %s\n";
        return String.format(eventDataFormat, this.isDoneToDataString(), super.description,
                Parser.formatDateTimeToData(dateAndTime));
    }

    /**
     * Returns string representation of event task.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        String eventStringFormat = "[" + TASK_ICON + "]%s (at: %s)";
        return String.format(eventStringFormat, super.toString(),
                Parser.formatDateTimeToMessage(dateAndTime));
    }
}
