public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + description;
    }

    public void setDone() {
        this.isDone = true;
    }
    public void setNotDone() {
        this.isDone = false;
    }
}
