public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String getSaveFormat() {
        return "D" + " | " + (getIsDone() ? 1 : 0) + " | " + getDescription() + " | " + getBy() +
                System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
