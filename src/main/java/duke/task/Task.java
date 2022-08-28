package duke.task;


/**
 * Represents a task.
 */
public abstract class Task {

    /** Status of this task */
    private boolean done;

    /** Name of this task */
    private String name;

    public Task(String name) {
       this.done = false;
       this.name = name;
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

    @Override
    public String toString() {
        return (this.isDone() ? "[X] " : "[ ] ") + this.name;
    }
}
