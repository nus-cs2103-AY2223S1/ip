public class Task {
    private String description;
    private boolean isComplete = false;

    public Task(String description) {
        this.description = description;
    }

    public String getStatusIcon() {
        return (isComplete ? "X" : " "); // mark completed task with X
    }

    public void markComplete() {
        this.isComplete = true;
    }

    public void markIncomplete() {
        this.isComplete = false;
    }

    public String toString() {
        return this.description;
    }
}
