public class Task {
    private boolean isComplete = false; // Initialized to false by default
    private String taskTitle;

    public Task(String taskTitle) {
        this.taskTitle = taskTitle;
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
