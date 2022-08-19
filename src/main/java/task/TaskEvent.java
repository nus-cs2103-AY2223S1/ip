package task;

public class TaskEvent extends Task {

    private final String taskAt;

    public TaskEvent(String taskName, String taskAt) {
        super(taskName);
        this.taskAt = taskAt;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", super.toString(), this.taskAt);
    }
}
