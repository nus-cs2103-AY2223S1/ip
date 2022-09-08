package duke;

/**
 * Deadline class which inherits from the Task class.
 */
public class Deadline extends Task {
    private final String by;

    /**
     * Constructor for the deadline class.
     *
     * @param description The description of the task.
     * @param by The date that the item is due by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = DukeTime.reformatDateTime(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toLine() {
        String line = "D";
        if (this.isDone) {
            line += ("*1*" + this.item + "*" + DukeTime.undoReformatDateTime(this.by));
        } else {
            line += ("*0*" + this.item + "*" + DukeTime.undoReformatDateTime(this.by));
        }
        return line;
    }
}
