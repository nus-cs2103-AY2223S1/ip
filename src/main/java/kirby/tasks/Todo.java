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
    public Todo(String description, boolean isDone) {
        super(description);
        if (isDone) {
            this.setCompleted();
        }
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
        return new StringBuilder().append("[T] ").append(this.getStatusIcon())
                .append(" ").append(this.description).toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileOutput() {
        return "kirby.tasks.Todo~" + this.description + "~" + this.getStatusIcon();
    }
}
