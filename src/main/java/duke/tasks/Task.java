package duke.tasks;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Represents a task.
 */
public abstract class Task {
    private final String description;
    private boolean done;

    /**
     * Initializes a new Task instance.
     *
     * @param description Description of the task
     * @param done Whether the task is completed or not
     */
    public Task(String description, boolean done) {
        this.description = description;
        this.done = done;
    }

    /**
     * Returns the task type as a String.
     *
     * @return Task type as a String
     */
    public abstract String getTaskType();

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
    public boolean getDone() {
        return this.done;
    }

    /**
     * Sets the task as done.
     */
    public void setDone() {
        this.done = true;
    }

    /**
     * Sets the task as not done.
     */
    public void setNotDone() {
        // This method is preferred over a toggle method as it is clearer on
        // its effects.
        this.done = false;
    }
}
