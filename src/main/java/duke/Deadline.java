package duke;

public class Deadline extends Task {
    private final String by;

    public Deadline(String item, String by) {
        super(item);
        this.by = JamieTime.reformatDateTime(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

    @Override
    public String toLine() {
        String line = "D";
        if (this.done) {
            line += ("*1*" + this.item + "*" + JamieTime.undoReformatDateTime(this.by));
        } else {
            line += ("*0*" + this.item + "*" + JamieTime.undoReformatDateTime(this.by));
        }
        return line;
    }
}
