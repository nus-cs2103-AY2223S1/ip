public class Deadline extends Task {

    protected String by;
    protected String id = "[D]";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return id + super.toString() + " (by: " + by + ")";
    }
}