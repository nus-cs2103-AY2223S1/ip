public class Task {
    protected boolean isDone;
    protected String description;
    protected int taskNumber;

    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone? "X": " ");
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public String getTaskName() {
        return this.description;
    }

    public int getTaskNumber() {
        return this.taskNumber;
    }
}
