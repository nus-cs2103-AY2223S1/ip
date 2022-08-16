public class Task {
    private boolean isComplete;
    private String taskTitle;

    public Task(String taskTitle, boolean isComplete) {
        this.taskTitle = taskTitle;
        this.isComplete = isComplete;
    }

    public String getTaskTitle() {
        return this.taskTitle;
    }

    public boolean getIsComplete() {
        return this.isComplete;
    }

    public void setIsComplete(boolean completedStatus) {
       this.isComplete = completedStatus;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isComplete ? "X" : " ", this.taskTitle);
    }
}
