package kirby.tasks;

/**
 * Todo class contains information of a Todo task.
 */
public class Todo extends Task {
    private static final int[] INVALID_DATE_ARR = new int[]{-1, -1, -1};
    /**
     * Constructor for the class Todo.
     *
     * @param description Description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getDate() {
        return INVALID_DATE_ARR;
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
        return "kirby.tasks.Todo~" + this.description;
    }
}
