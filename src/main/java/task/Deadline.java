package task;

public class Deadline extends Task {
    private final String deadline;

    public Deadline(String taskItem, String deadline) {
        super(taskItem);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String deadlineDisplay = String.format(" (by: %s)", this.deadline);
        return "[D]" + super.toString() + deadlineDisplay;
    }

    @Override
    public String encode() {
        int markedStatus = getIsMarked() ? 1 : 0;
        return String.format("D,%d,%s,%s\n", markedStatus, getTaskItem(), deadline);
    }
}
