package Duke;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatus() {
        return (isDone ? "x" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void UnmarkAsDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String binarytoString() {
        return isDone
                ? "1"
                : "0";
    }

    public String storeToString() {
        return "Task type|binarytoString|task description";
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "]" + " " + this.description;
    }

}
