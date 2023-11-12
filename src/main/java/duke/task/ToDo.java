package duke.task;

/**
 * ToDo, a type of Task.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String storedString() {
        return "T | " + super.storedString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
