package duke.task;

public abstract class Task {
    protected String name;
    protected Boolean isDone = false;
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
        String mark = isDone ? "X" : "";
        return "[" + mark + "] " + this.name;
    }
}
