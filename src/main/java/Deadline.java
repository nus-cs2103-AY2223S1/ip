public class Deadline extends Task {
    private String taskDeadline;

    public Deadline(String taskDescription, String taskDeadline) {
        super(taskDescription);
        this.taskDeadline = taskDeadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + taskDeadline + ")";
    }
}
