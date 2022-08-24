public class Deadline extends Task {
    private String taskDeadline;

    public Deadline(String taskDescription, String taskDeadline) {
        super(taskDescription);
        this.taskDeadline = taskDeadline;
    }

    public Deadline(String taskDescription, boolean isTaskDone, String taskDeadline) {
        super(taskDescription, isTaskDone);
        this.taskDeadline = taskDeadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.taskDeadline + ")";
    }

    public String toFileString() {
        return "D | " + super.toFileString() + " | " + this.taskDeadline + "\n";
    }
}
