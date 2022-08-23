package duke;

public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D] " + this.getStatusIcon() + " " + super.description + " (by:" + by + ")";
    }

    @Override
    public String toSaveVersion() {
        if (this.isDone()) {
            return "D|1|" + super.description + "|" + this.by + "\n";
        } else {
            return "D|0|" + super.description + "|" + this.by + "\n";
        }
    }
}
