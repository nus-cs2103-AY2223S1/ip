package duke;

import java.io.Serializable;
public abstract class Task implements Serializable {

    private String item;
    private boolean isCompleted;

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
