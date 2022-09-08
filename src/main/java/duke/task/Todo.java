package duke.task;

/**
 * Represents a task with no deadline.
 */
public class Todo extends Task {

    /**
     * Initialises a <code>Todo</code> object with the name as its description.
     * @param name The description of the <code>Todo</code> task.
     * @param tag The tag of the <code>Todo</code> task.
     */
    public Todo(String name, String tag) {
        super(name, tag);
    }

    /**
     * Returns the description of the <code>Todo</code> task.
     * @return The description of the <code>Todo</code> task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
