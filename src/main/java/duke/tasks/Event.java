package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Represents a task with a specific event duration (e.g., starts at 4pm and ends at 6pm).
 */
public class Event extends Task {
    /** Task identifier used by Parser **/
    public static final String TASK_WORD = "event";
    private final LocalDateTime at;

    /**
     * Initializes a new Event instance.
     *
     * @param description Description of the task
     * @param done Whether the task is completed or not
     * @param at The event duration for this task
     */
    public Event(String description, boolean done, LocalDateTime at) {
        super(description, done);
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
     * Returns the task type as a String.
     *
     * @return Task type as a String
     */
    @Override
    public String getTaskType() {
        return "Event";
    }

    /**
     * Returns a string representation of the Deadline instance.
     *
     * @return String representation of the Deadline instance
     */
    @Override
    public String toString() {
        String checkbox = this.getDone() ? "[E][X]" : "[E][ ]";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
        String dateFormatted = "(at: " + this.at.format(formatter) + ")";
        return checkbox + " " + super.getDescription() + " " + dateFormatted;
    }
}
