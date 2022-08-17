public class Task {
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return taskName;
    }
}
