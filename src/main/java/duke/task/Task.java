package duke.task;

/**
 * An abstract class that represents a task object
 */
public abstract class Task {
    protected String name;
    protected Boolean isDone = false;

    /**
     * Constructor for a task
     *
     * @param name
     */
    public Task(String name) {
        this.name = name;
    }
    /**
     * sets a task to be done
     *
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * sets a task to be not done
     *
     */
    public void setUndone() {
        this.isDone = false;
    }

    public abstract String writeData();

    @Override
    public String toString() {
        String mark = isDone ? "X" : " ";
        return "[" + mark + "] " + this.name;
    }
}
