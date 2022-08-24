public class Deadline extends Task {
    private String by;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }

    public Deadline(String desc, String by, boolean isDone) {
        super(desc, isDone);
        this.by = by;
    }

    @Override
    public String toSaveFormat() {
        return "D " + super.toSaveFormat() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + this.by + ")";
    }
}
