package duke;

/**
 * Encapsulates a task with a deadline.
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean done) {
        super(description, done);
        this.by = by;
    }

    @Override
    public String toSaveData() {
        return "D|" + (super.isDone ? "1|" : "0|") + this.description + "|" + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
