package duke;

/**
 * Encapsulates a generic task to be done.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean done) {
        super(description, done);
    }

    @Override
    public String toSaveData() {
        return "T|" + (super.isDone ? "1|" : "0|") + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
