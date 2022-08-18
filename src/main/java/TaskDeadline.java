public class TaskDeadline extends Task {

    private final String taskBy;

    public TaskDeadline(String taskName, String taskBy) {
        super(taskName);
        this.taskBy = taskBy;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.taskBy);
    }
}
