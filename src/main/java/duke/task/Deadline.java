package duke.task;

public class Deadline extends Task {

    protected String returnBy;

    public Deadline(String description, String returnBy) {
        this.description = description;
        this.isDone = false;
        this.returnBy = returnBy;
    }

    public Deadline(String description, boolean isDone, String returnBy) {
        this.description = description;
        this.isDone = isDone;
        this.returnBy = returnBy;
    }
    @Override
    public String saveStringFormat() {
        return String.format("D | %d | %s | %s", this.isDone? 1 : 0, this.description, this.returnBy);
    }

    @Override
    public String toString() {
        return "[D] " + "[" + this.getStatusIcon() + "] " + this.description
                + " (by: " + this.returnBy + ")";

    }
}
