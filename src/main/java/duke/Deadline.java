package duke;

public class Deadline extends Task{
    private final String tag = "[D]";
    private final String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }


    @Override
    public String toString() {
        return tag + "[" + this.getStatusIcon()  + "] " + this.getTaskName() + "(" + deadline + ")";
    }
}
