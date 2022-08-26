package task;

/**
 * Represents an abstract Task object.
 *
 * @author ShummyOwnzYou
 * @version 0.1
 */

public abstract class Task {
    private String name;
    private boolean done;

    /**
     * Initializes Task object with the task description.
     *
     * @param name Task name to be stored
     */

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    /**
     * Marks the task as done if undone, unmarks the task otherwise.
     */

    public void toggleDoneness() {
        this.done = !this.done;
    }

    /**
     * Returns true if task is done, false otherwise.
     *
     * @return true if task is done, false otherwise.
     */

    public boolean isDone() {
        return this.done;
    }

    /**
     * Converts the task into a string to be stored in the Storage class.
     *
     * @return the String containing the Task information to be stored
     */

    public String stringify() {
        if (this.done) {
            return "Y##" + this.name;
        } else {
            return "N##" + this.name;
        }
    }

    /**
     * Returns the String representation of the Task.
     *
     * @return the String representation of the Task
     */

    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
