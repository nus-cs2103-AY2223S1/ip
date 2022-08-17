public class Deadline extends Task {

    private String deadLine;

    public Deadline(String taskName, String deadLine) {
        super(taskName);
        this.deadLine = deadLine;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadLine + ")";
    }
}
