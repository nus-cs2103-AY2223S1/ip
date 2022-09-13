package duke.tasks;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Represents a task with a specific deadline.
 *
 * @author sikai00
 */
public class Deadline extends Task {
    /** Task identifier used by Parser **/
    public static final String TASK_WORD = "deadline";
    private final LocalDateTime by;

    /**
     * Initializes a new Deadline instance.
     * @param description Description of the task
     * @param isDone Whether the task is completed or not
     * @param by The deadline for this task
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns an Optional of the time the task is due by.
     * @return Optional of the time the task is due by
     */
    @Override
    public Optional<LocalDateTime> getTime() {
        return Optional.of(this.by);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTaskWord() {
        return Deadline.TASK_WORD;
    }

    /**
     * Returns a string representation of the Deadline instance.
     * @return String representation of the Deadline instance
     */
    @Override
    public String toString() {
        String checkbox = this.getIsDone() ? "[D][X]" : "[D][ ]";
        String dateFormatted = "(by: " + this.by.format(FORMATTER) + ")";
        return checkbox + " " + super.getDescription() + " " + dateFormatted;
    }
}
