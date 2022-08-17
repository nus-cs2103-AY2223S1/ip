public class Deadline extends Task {
    private String deadline;
    public Deadline(String taskDescription, String taskDeadline) {
        super(taskDescription);
        this.deadline = taskDeadline;
    }

    @Override
    public String toString() {
        String taskStatusIndicator = "[D]" + (this.isCompleted() ? "[X] " : "[ ] ");
        return taskStatusIndicator + this.getTaskDescription() + " (by: " + this.deadline + ")";
    }
}
