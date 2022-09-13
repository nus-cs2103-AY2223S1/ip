package duke.tasks;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Represents a task without any time specifications.
 *
 * @author sikai00
 */
public class Todo extends Task {
    /** Task identifier used by Parser **/
    public static final String TASK_WORD = "todo";

    /**
     * Initializes a new Todo instance.
     *
     * @param description Description of the task
     * @param isDone Whether the task is completed or not
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns an empty Optional.
     *
     * @return Empty Optional
     */
    @Override
    public Optional<LocalDateTime> getTime() {
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTaskWord() {
        return Todo.TASK_WORD;
    }

    /**
     * Returns a string representation of the Todo instance.
     *
     * @return String representation of the Todo instance
     */
    @Override
    public String toString() {
        String checkbox = this.getIsDone() ? "[T][X]" : "[T][ ]";
        return checkbox + " " + super.getDescription();
    }
}
