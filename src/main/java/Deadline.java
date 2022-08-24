public class Deadline extends Task {
    private final String dueBy;

    public Deadline(String taskName, String dueBy) {
        super(taskName);
        this.dueBy = dueBy;
    }

    @Override
    public String toString() {
        return "[D]" + (this.done ? "[X] " : "[ ] ") + this.taskName + " (by:" + this.dueBy + ")";
    }
}
