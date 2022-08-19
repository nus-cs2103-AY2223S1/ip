public abstract class Task {
    protected String description;
    protected boolean status;

    protected String taskType;
    public Task(String description, String taskType) {
        this.description = description;
        this.status = false;
        this.taskType = taskType;
    }
    public String getDescription() {
        return description; // mark done task with X
    }
    public String getStatus() {
        return (status ? "X" : " "); // mark done task with X
    }

    public String getTaskType() {
        return taskType;
    }
    public void changeStatus(boolean status) {
        this.status = status;
    }
}
