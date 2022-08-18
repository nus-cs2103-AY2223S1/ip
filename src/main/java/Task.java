public class Task {
    protected String description;
    protected boolean isDone;
    protected int index;
    protected static int indexCount = 1;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.index = indexCount;
        indexCount++;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }
}
