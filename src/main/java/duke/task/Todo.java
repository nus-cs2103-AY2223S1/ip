package duke.task;

/**
 * Encapsulates a To-Do.
 */
public class Todo extends Task {

    /**
     * Constructor for a To-do item.
     *
     * @param name Description of task
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Overloaded constructor for To-do item.
     * @param data String passed in from Storage
     */
    public Todo(String[] data) {
        super(data[2], (data[1].equals("1")));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toStringWritable() {
        return " T |" + super.toStringWritable();
    }
}
