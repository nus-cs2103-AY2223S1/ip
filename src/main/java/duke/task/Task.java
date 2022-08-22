package duke.task;
public class Task {
    /**
     * Parent class to manage all the Task
     */
    protected String description;
    protected boolean isDone;
    protected String symbol;

    public Task(String description, String symbol) {
        this.description = description;
        this.isDone = false;
        this.symbol = symbol;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getSymbol() { return this.symbol; }

    public String getInfo() {
        return (getSymbol() + "--" + getStatusIcon() + "--" + getDescription());
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }
}
