public class Deadline extends Task {
    private String by;

    public Deadline(String title, boolean done, String by) {
        super(title, done);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }
}
