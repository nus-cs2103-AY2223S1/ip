package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Represents a task.
 *
 * @author sikai00
 */
public abstract class Task {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
    private final String description;
    private boolean isDone;

    /**
     * Initializes a new Task instance.
     *
     * @param description Description of the task
     * @param isDone Whether the task is completed or not
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the task word as a String.
     *
     * @return Task word as a String
     */
    // This is needed as all Tasks has a TASK_WORD, but the abstract class Task does not.
    public abstract String getTaskWord();

    /**
     * Returns an Optional of the time of the task.
     *
     * @return Optional of the time of the task
     */
    public abstract Optional<LocalDateTime> getTime();

    /**
     * Returns a description of the task.
     *
     * @return Description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether the task is done or not.
     *
     * @return Whether the task is done or not
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Sets the task as done.
     */
    public void setIsDone() {
        this.isDone = true;
    }

    /**
     * Sets the task as not done.
     */
    public void setIsNotDone() {
        // This method is preferred over a toggle method as it is clearer with its effects.
        this.isDone = false;
    }
}
