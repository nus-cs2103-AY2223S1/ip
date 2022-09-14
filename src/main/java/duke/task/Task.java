package duke.task;

/**
 * Representing a task.
 */
public class Task {
    protected String name;
    protected boolean done;

    /**
     * Constructor for a task.
     *
     * @param name
     */
    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    /**
     * Constructor for a task with known completion status.
     *
     * @param name
     */
    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    /**
     * Marks a task as complete.
     */
    public void completed() {
        this.done = true;
    }

    /**
     * Marks a task as incomplete.
     */
    public void uncompleted() {
        this.done = false;
    }

    /**
     * Gives the String representation of the status icon.
     *
     * @return String representing status icon.
     */
    public String getStatusIcon() {
        return (this.done) ? "X" : " ";
    }

    /**
     * Returns the task name.
     *
     * @return String representing the task name.
     */
    public String getTaskName() {
        return this.name;
    }

    /**
     * Represents the task as a String.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.name);
    }

    /**
     * Represents the task as a save-friendly String.
     *
     * @return Save-friendly String representation of the task.
     */
    public String savedString() {
        return String.format("%s,%s", (this.done) ? "Y" : "N", this.name);
    }


}
