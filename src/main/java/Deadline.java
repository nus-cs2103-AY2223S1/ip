public class Deadline extends Task {
    private String by;

    Deadline(String description, boolean isComplete, String by) {
        super(description, isComplete);
        this.by = by;
    }

    Deadline(String description, String by) {
        this(description, false, by);
    }

    String getDate() {
        return this.by;
    }

    @Override
    String toStorageFormat() {
        return "D | " + super.toStorageFormat() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
