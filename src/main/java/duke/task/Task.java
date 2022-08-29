package duke.task;

import java.io.Serializable;

/**
 * {@code Task} includes different types of tasks and allows operations to query them
 */
public abstract class Task implements Serializable {
    private String description;
    private boolean done;

    /**
     * Constructor for {@code Task}
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * To retrieve the description of a {@code Task}
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * To check whether a {@code Task} has been done
     * @return true if it is done, and false if it is not
     */
    public boolean isDone() {
        return this.done;
    }

    /**
     * To mark the {@code Task} as done
     */
    public void markAsDone() {
        this.done = true;
    }

    /**
     * To mark the {@code Task} as undone
     */
    public void markAsNotDone() {
        this.done = false;
    }

    /**
     * To display the {@code Task} and whether is has been done
     * @return the string representation of the {@code Task}
     */
    public String toString() {
        if (this.isDone()) {
            return("[X] " + this.description);
        }
        else {
            return("[ ] " + this.description);
        }
    }
}
