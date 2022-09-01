package duke.task;


/**
 * Represents a task.
 */
public abstract class Task {

    /** Status of this task */
    private boolean done;

    /** Name of this task */
    private String name;

    /**
     * Constructs a new Task object with given description.
     *
     * @param description of the task.
     */
    public Task(String description) {
        this.done = false;
        this.name = description;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Returns a specially formatted string.
     *
     * @return Specially formatted string.
     */
    public abstract String toMemoryString();

    /**
     * Returns string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return (this.isDone() ? "[X] " : "[ ] ") + this.name;
    }
}
