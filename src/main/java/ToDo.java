/**
 * A class that stores something to do.
 */
public class ToDo extends Task {

    /**
     * Creates a todo item.
     * @param description Description of todo.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
