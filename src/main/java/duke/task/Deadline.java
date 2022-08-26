package duke.task;

public class Deadline extends Task {
    protected String deadline;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String inputToTxt() {
        return String.format("D | %s | %s | %s\n",
                (this.isDone ? "1" : "0"),
                this.taskName, this.deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}