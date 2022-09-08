package duke;

/**
 * ToDo class which inherits from the Task class.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toLine() {
        String line = "T";
        if (this.isDone) {
            line += ("*1*" + this.item);
        } else {
            line += ("*0*" + this.item);
        }
        return line;
    }
}
