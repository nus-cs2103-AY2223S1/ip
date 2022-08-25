public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String fileStatus() {
        return "D | " + super.fileStatus() + "|" + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + this.by + ")";
    }
}
