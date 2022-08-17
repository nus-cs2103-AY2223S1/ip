public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        setIsDone(false);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public void setIsDone(boolean value) {
        this.isDone = value;
    }

    public void markAsDone() {
        setIsDone(true);
    }

    public void unmarkAsDone() {
        setIsDone(false);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}