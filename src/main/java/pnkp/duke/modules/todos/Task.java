package pnkp.duke.modules.todos;

import static java.lang.String.format;

/**
 * Parent class for tasks.
 */
public abstract class Task {
    private String name;
    private boolean done;

    /**
     * Constructor
     * @param name The name of the task.
     */
    public Task(String name) {
        this(name, false);
    }

    /**
     * Constructor
     * @param name The name of the task
     * @param done Whether the task is done.
     */
    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    /**
     * Sets whether the task is done.
     * @param done Whether the task is done.
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return format("[%s] %s", this.done ? "X" : " ", this.name);
    }
}
