package pikachu.task;
abstract public class Task {
    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone(boolean newDone) {
        this.isDone = newDone;
    }

    abstract public String getName();
    abstract public String getTiming();

    @Override
    public String toString() {
        return '[' + getStatusIcon() + "] " + description;
    }
}
