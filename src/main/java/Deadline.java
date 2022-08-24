public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getWriteString() {
        return String.format("D | %s | %s", super.getWriteString(), this.by);
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.by);
    }
}