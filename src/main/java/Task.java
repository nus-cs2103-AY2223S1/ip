public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public abstract String getTaskType();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    String saveStringToFile() {
        return String.format("%s\n%s\n%d\n", getTaskType(), description, isDone ? 1 : 0);
    }
}


