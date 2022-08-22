public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }

    @Override
    public String toDataEntry() {
        int mark = this.isDone ? 1 : 0;
        return String.format("D # %d # %s # %s\n", mark, this.description, this.by);
    }
}
