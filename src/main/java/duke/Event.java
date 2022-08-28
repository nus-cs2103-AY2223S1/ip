package duke;

/**
 * Event class which inherits from the Task class.
 */
public class Event extends Task {
    private String at;

    /**
     * Constructor for the deadline class.
     *
     * @param item The item that would be happening.
     * @param at The date that the item is at.
     */
    public Event(String item, String at) {
        super(item);
        this.at = JamieTime.reformatDateTime(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.at + ")";
    }

    @Override
    public String toLine() {
        String line = "E";
        if (this.done) {
            line += ("*1*" + this.item + "*" + JamieTime.undoReformatDateTime(this.at));
        } else {
            line += ("*0*" + this.item + "*" + JamieTime.undoReformatDateTime(this.at));
        }
        return line;
    }
}
