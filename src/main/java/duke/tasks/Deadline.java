package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Represents a task with a specific deadline.
 */
public class Deadline extends Task {
    /** Task identifier used by Parser **/
    public static final String TASK_WORD = "deadline";
    private final LocalDateTime by;

    /**
     * Initializes a new Deadline instance.
     * @param description Description of the task
     * @param done Whether the task is completed or not
     * @param by The deadline for this task
     */
    public Deadline(String description, boolean done, LocalDateTime by) {
        super(description, done);
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
     * Returns the task type as a String.
     * @return Task type as a String
     */
    @Override
    public String getTaskType() {
        return "Deadline";
    }

    /**
     * Returns a string representation of the Deadline instance.
     * @return String representation of the Deadline instance
     */
    @Override
    public String toString() {
        String checkbox = this.getDone() ? "[D][X]" : "[D][ ]";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
        String dateFormatted = "(by: " + this.by.format(formatter) + ")";
        return checkbox + " " + super.getDescription() + " " + dateFormatted;
    }
}
