package duke;

abstract public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    abstract public String getDataFormat();

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUnDone() {
        this.isDone = false;
    }

    public String[] splitDescriptionToWords() {
        return description.split(" ");
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
