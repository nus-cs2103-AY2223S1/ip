public class Task {
    private final String taskName;

    Task(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return this.taskName + "\n";
    }
}
