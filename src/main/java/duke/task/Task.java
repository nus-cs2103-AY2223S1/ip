package duke.task;

/**
 * Represents a task (template for future tasks)
 *
 * @author benjytan45678
 * @version 0.1
 */
public abstract class Task {
    private boolean hasCompleted = false;
    private String name;

    /**
     * Instantiates a task with specified name.
     *
     * @param name name of the event task
     */
    public Task(String name) {

        this.name = name;
    }

    /**
     * Set task to completed
     */
    public void setCompleted() {

        this.hasCompleted = true;
    }


    /**
     * Return completion status of the task
     */
    public boolean getHasCompleted() {

        return this.hasCompleted;
    }

    /**
     * Set task to Uncompleted
     */
    public void setUncompleted() {
        this.hasCompleted = false;
    }

    /**
     * Creates a String representation of the task
     *
     */
    public String toString() {
        if (hasCompleted) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }

    /**
     * Creates a simplified version of the task to be stored in local file
     *
     */
    public abstract String parse();
}
