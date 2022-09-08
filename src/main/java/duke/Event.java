package duke;

/**
 * Event class which inherits from the Task class.
 */
public class Event extends Task {
    private final String at;

    /**
     * Constructor for the deadline class.
     *
     * @param description The description of the task.
     * @param at The date that the item is at.
     */
    public Event(String description, String at) {
        super(description);
        this.at = DukeTime.reformatDateTime(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    @Override
    public String toLine() {
        String line = "E";
        if (this.isDone) {
            line += ("*1*" + this.item + "*" + DukeTime.undoReformatDateTime(this.at));
        } else {
            line += ("*0*" + this.item + "*" + DukeTime.undoReformatDateTime(this.at));
        }
        return line;
    }
}
