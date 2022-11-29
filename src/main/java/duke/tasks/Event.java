package duke.tasks;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Represents a task with a specific event duration (e.g., starts at 4pm and ends at 6pm).
 *
 * @author sikai00
 */
public class Event extends Task {
    /** Task identifier used by Parser **/
    public static final String TASK_WORD = "event";
    private final LocalDateTime at;

    /**
     * Initializes a new Event instance.
     *
     * @param description Description of the task
     * @param isDone Whether the task is completed or not
     * @param at The event duration for this task
     */
    public Event(String description, boolean isDone, LocalDateTime at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Returns an Optional of the time of event.
     *
     * @return Optional of the time of event
     */
    @Override
    public Optional<LocalDateTime> getTime() {
        return Optional.of(this.at);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTaskWord() {
        return Event.TASK_WORD;
    }

    /**
     * Returns a string representation of the Deadline instance.
     *
     * @return String representation of the Deadline instance
     */
    @Override
    public String toString() {
        String checkbox = this.getIsDone() ? "[E][X]" : "[E][ ]";
        String dateFormatted = "(at: " + this.at.format(FORMATTER) + ")";
        return checkbox + " " + super.getDescription() + " " + dateFormatted;
    }
}
