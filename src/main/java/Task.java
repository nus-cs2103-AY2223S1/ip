public class Task {
    protected String taskType;
    protected String description;
    protected boolean isDone;

    public Task(String task) {
        String[] temp = task.split(" ", 2);
        this.taskType = temp[0];
        this.description = temp[1];
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "]\t" + description;
    }
}
