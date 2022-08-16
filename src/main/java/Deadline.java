public class Deadline extends Task {
    private final String by;

    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getTaskIcon() {
        return "D";
    }

    @Override
    public String toString() {
        return "[" + this.getTaskIcon() + "]" + super.toString() + "(by: " + this.by + ")";
    }
}
