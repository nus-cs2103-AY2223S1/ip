package Duke.DukeTasks;

public class Todo extends Task {
    /**
     * Constructor for Todo.
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