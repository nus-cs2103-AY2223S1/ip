package component;

/**
 * Public class ToDo that extends Task.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description, "T");
    }

    public ToDo(String description, boolean isDone) {
        super(description, "T", isDone);
    }

    @Override
    public String toString() {
        return "[" + this.getCode() + "]" + super.toString();
    }
}