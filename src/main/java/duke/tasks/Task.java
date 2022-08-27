package duke.tasks;

/**
 * Represents a Task in Duke.
 * 
 * @author Ramanathan Kumarappan
 */
public class Task {
    private String description;
    private Boolean isDone;

    /**
     * Constructor for Task.
     * 
     * @param description - The description of the Task.
     */
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Mark the Task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark the Task as not done.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the Task in a save friendly format.
     * 
     * @return The Task in a save string format.
     */
    public String saveString() {
        return (isDone ? 1 : 0) + " | " + description;
    }

    /**
     * Returns a string representation of the Task.
     * @return The string representation of the Task.
     */
    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "]" + " " + this.description;
    }
}
