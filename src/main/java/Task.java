public abstract class Task {
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";
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

    public String toString() {
        return String.format(ANSI_YELLOW + "[ %s ] [ %s ] %s", this.getTaskType(), this.getStatus(), this.getDescription() + ANSI_RESET);
    }
}
