public class Deadline extends Task{
    private String by;
    public Deadline(String input, boolean isDone) {
        super(isDone);
        String[] strArr = input.split("/by");
        System.out.println(strArr[0]);
        this.description = strArr[0].trim();
        this.by = strArr[1].trim();
    }

    public Deadline(String input, String by, boolean isDone) {
        super(isDone);
        this.description = input;
        this.by = by;
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.Deadline;
    }

    @Override
    public String toDataForm() {
        String done = this.isDone ? "1" : "0";
        return "D|" + done + "|" + this.description + "|" + this.by + "\n";
    }

    @Override
    public String toString() {
        String head = "[D][" + this.getStatusIcon() + "] ";
        String body = this.description + " (by: " + this.by + ")";
        return head + body;
    }
}
