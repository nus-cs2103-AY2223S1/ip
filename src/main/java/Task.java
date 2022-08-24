public class Task {

    public enum TaskType {
        ToDo,
        Deadline,
        Event
    }
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTaskType() {
        return this.taskType == TaskType.ToDo
                ? "T"
                : this.taskType == TaskType.Event
                ? "E"
                : this.taskType == TaskType.Deadline
                ? "D"
                : " ";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String type = "[" + this.getTaskType() + "]";
        return type + "[" + this.getStatusIcon() + "] " + this.description;
    }
}
