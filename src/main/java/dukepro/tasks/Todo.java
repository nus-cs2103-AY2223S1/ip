package dukepro.tasks;

/**
 * Class for Todo.
 */
public class Todo extends Task {
    /**
     * Creates a Todo.
     *
     * @return A Todo.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns String format of this class
     *
     * @return A String.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns String format of this class to be
     * saved to data/tasklist.txt.
     *
     * @return A String.
     */
    @Override
    public String fileForm() {
        return "T" + "," + super.fileForm();
    }
}
