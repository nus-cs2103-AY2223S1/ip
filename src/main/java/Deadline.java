
public class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toStorageString() {
        String taskString = super.toStorageString();
        return "D" + Task.STORAGE_DELIMITER + taskString + Task.STORAGE_DELIMITER + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
