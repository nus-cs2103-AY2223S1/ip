package duke;

import java.io.Serializable;

/** Represents a Task abstract class. Supports a description and the isCompleted boolean */
public abstract class Task implements Serializable {

    private String item;
    private boolean isCompleted;

    /**
     * Abstract constructor for Task object (invoked through subclasses)
     * @param item description
     */
    public Task(String item) {
        this.item = item;
        this.isCompleted = false;
    }

    /**
     * Toggles the isCompleted variable between true/false
     */
    public void toggleComplete() {
        this.isCompleted = !this.isCompleted;
    }

    /**
     * Returns the completed variable
     * @return completed
     */
    public boolean getIsCompleted() {
        return this.isCompleted;
    }

    public abstract boolean isDated();

    /**
     * Returns a string containing state and params of Task item for writing to file
     * @return String
     */
    public String toStoredString() {
        if (isCompleted) {
            return "T/" + item;
        }
        return "F/" + item;
    }

    /**
     * Returns String representation of a task. This is built on by subclasses
     * @return String representation
     */
    @Override
    public String toString() {
        if (isCompleted) {
            return "[X] " + item;
        }
        return "[ ] " + item;
    }

}
