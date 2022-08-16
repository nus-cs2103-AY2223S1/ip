public class Task {
    private boolean isComplete;
    private String toDoName;

    public Task(String toDoName, boolean isComplete) {
        this.toDoName = toDoName;
        this.isComplete = isComplete;
    }

    public String getToDoName() {
        return this.toDoName;
    }

    public boolean getIsComplete() {
        return this.isComplete;
    }

    public void setIsComplete(boolean completedStatus) {
       this.isComplete = completedStatus;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isComplete ? "X" : " ", this.toDoName);
    }
}
