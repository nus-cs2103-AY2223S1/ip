package duke;

import java.io.Serializable;

public abstract class Task implements Serializable {

    private String item;
    private boolean completed;

    public Task(String item) {
        this.item = item;
        this.completed = false;
    }

    /**
     * Toggles the completed variable between true/false
     */
    public void completeToggle() {
        this.completed = !this.completed;
    }

    /**
     * Returns the completed variable
     * @return completed
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * Returns String representation of a task. This is built on by subclasses
     * @return String representation
     */
    @Override
    public String toString() {
        if (completed) {
            return "[X] " + item;
        }

        return "[ ] " + item;
    }

}
