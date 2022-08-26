public class Deadline extends Task {
    final String dueBy;

    public Deadline(String taskName, String dueBy, boolean isDone) {
        super(taskName, isDone);
        this.dueBy = dueBy;
    }

    @Override
    public String taskToFileString() {
        return " D " + "| " + (this.done ? "1 " : "0 ") + "|" + this.taskName + "|" + this.dueBy;
    }

    @Override
    public String toString() {
        return "[D]" + (this.done ? "[X]" : "[ ]") + this.taskName + "(by:" + this.dueBy + ")";
    }
}
