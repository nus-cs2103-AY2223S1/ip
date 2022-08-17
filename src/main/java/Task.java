public class Task {
    // Class Fields
    private String taskName;

    // Constructor
    public Task(String taskName) {
        this.taskName = taskName;
    }

    // Methods
    @Override
    public String toString() {
        return taskName;
    }
}