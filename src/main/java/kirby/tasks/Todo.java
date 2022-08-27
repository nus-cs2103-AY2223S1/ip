package kirby.tasks;

/**
 * Todo class contains information of a Todo task.
 */
public class Todo extends Task {
    /**
     * Constructor for the class Todo.
     *
     * @param description description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getDate() {
        return new int[]{-1, -1, -1};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T] " + this.getStatusIcon() + " " + this.description ;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileOutput() {
        return "kirby.Todo~" + this.description;
    }
}