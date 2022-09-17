package duke.tasks;

/**
 * This class represents a to-do.
 */
public class Todo extends Task {

    /**
     * Constructs a new to-do with the specified description.
     * @param name Description of the to-do.
     */
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String toDataString() {
        return String.format("[T] | %d | %s",
                isMarked() ? 1 : 0,
                getName());
    }
}
