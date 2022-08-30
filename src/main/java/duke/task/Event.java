package duke.task;

import java.time.LocalDate;

/**
 * Represents the Event Task object.
 */
public class Event extends Task {
    public static final String DELIMITER = " /at ";

    private LocalDate time;

    /**
     * Constructs a new Event object from description and time.
     * @param description Description for the Event object.
     * @param time Time for the Event object.
     */
    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    /**
     * Constructs a new Event object from description, time and isDone status.
     * @param description Description for the Event object.
     * @param time Time for the Event object.
     * @param isDone IsDone status for the Event object.
     */
    public Event(String description, LocalDate time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * Gets the string representation of the Event object.
     * @return String representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time + ")";
    }

    /**
     * String representation of the Event object to be saved to and loaded from.
     * @return String representation of the Event object to be saved.
     */
    @Override
    public String toSave() {
        return "E" + Task.SAVE_SEPARATOR + this.getIsDoneString() + Task.SAVE_SEPARATOR + this.getDescription()
                + Task.SAVE_SEPARATOR + this.time;
    }
}
