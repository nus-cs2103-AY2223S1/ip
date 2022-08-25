public class Deadline extends Task {
    String by;

    public Deadline(String title, String by) {
        super(title);
        this.by = by;
    }

    public Deadline(String title, String by, Boolean isDone) {
        super(title, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }

    public String toSaveString() {
        return "D|" + super.toSaveString() + "|" + by;
    }
}
