package duke;

/**
 * A ToDo task.
 */
public class ToDo extends Task {

    public ToDo(String title, boolean done) {
        super(title, "todo", done);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
