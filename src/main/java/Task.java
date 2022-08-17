public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName){
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return taskName;
    }
}
