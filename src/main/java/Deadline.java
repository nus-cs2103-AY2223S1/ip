public class Deadline extends Task{
    private String by;
    public Deadline(String input) {
        String[] strArr = input.split("/by");
        this.description = strArr[0].trim();
        this.by = strArr[1].trim();
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.Deadline;
    }

    @Override
    public String toString() {
        String head = "[D][" + this.getStatusIcon() + "] ";
        String body = this.description + " (by: " + this.by + ")";
        return head + body;
    }
}
