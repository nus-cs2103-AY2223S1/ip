package duke.tasks;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Represents a task without any time specifications.
 */
public class Todo extends Task {
    /** Task identifier used by Parser **/
    public static final String TASK_WORD = "todo";

    /**
     * Initializes a new Todo instance.
     * @param description Description of the task
     * @param done Whether the task is completed or not
     * @param at The event duration for this task
     */
    public Todo(String description, boolean done) {
        super(description, done);
    }

    /**
     * Returns an empty Optional.
     * @return Empty Optional
     */
    @Override
    public Optional<LocalDateTime> getTime() {
        return Optional.empty();
    }

    /**
     * Returns the task type as a String.
     * @return Task type as a String
     */
    @Override
    public String getTaskType() {
        return "Todo";
    }

    /**
     * Returns a string representation of the Todo instance.
     * @return String representation of the Todo instance
     */
    @Override
    public String toString() {
        String checkbox = this.getDone() ? "[T][X]" : "[T][ ]";
        return checkbox + " " + super.getDescription();
    }
}
