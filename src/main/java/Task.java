public class Task {
    private boolean isComplete = false; // Initialized to false by default
    private String taskDescription;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public boolean getIsComplete() {
        return this.isComplete;
    }

    public void setIsComplete(boolean completedStatus) {
       this.isComplete = completedStatus;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isComplete ? "X" : " ", this.taskDescription);
    }
}
