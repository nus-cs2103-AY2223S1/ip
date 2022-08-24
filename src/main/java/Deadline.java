public class Deadline extends Task {
    protected String type = "D";
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString() + "(by: " + by + ")";
    }
}
