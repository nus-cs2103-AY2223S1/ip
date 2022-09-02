public class Deadline extends Task {
    protected String by;

    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String fileFormat() {
        return String.format("deadline | %s | %s | %b", super.description, by, super.isDone);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}
