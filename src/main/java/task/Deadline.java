package task;

public class Deadline extends Task {
    String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    @Override
    protected String getTypeIndicator() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), by);
    }
}
