package duke.task;

/**
 * Represents a todo task.
 */
public class ToDos extends Task {

    /**
     * Constructor for ToDos class.
     *
     * @param task task in String.
     * @param done whether task is done.
     *             true if marked.
     *             false if unmarked.
     */
    public ToDos(String task, boolean done) {
        super(task, done);
    }

    /**
     * Returns a String representation of todo task.
     *
     * @return String representation of todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a String representation of todo task in save file format.
     *
     * @return String representation of todo task in save file format.
     */
    @Override
    public String toSaveString() {
        return "todo " + super.toSaveString();
    }
}
