package duke.task;

/**
 * Represents tasks without any date/time attached to it.
 *
 * @author ish1506
 */
public class Todo extends Task {
    /**
     * Constructs a <code>Todo</code>.
     *
     * @param name the name of the todo.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Constructs a <code>Todo</code>.
     *
     * @param name   the name of the todo.
     * @param isDone the status of the todo
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Returns a <code>Todo</code> instance from its string representation.
     *
     * @param inputString the string representation of the <code>Todo</code>.
     * @return the <code>Todo</code> instance.
     */
    public static Todo fromString(String inputString) {
        boolean isDone = inputString.charAt(4) == 'X';
        String name = inputString.substring(7);
        return new Todo(name, isDone);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}