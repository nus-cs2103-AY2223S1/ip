/**
 * A task that is not time sensitive.
 */
public class ToDoTask extends Task{

    /**
     * Constructor for an todo task.
     * @param description A description of the todo
     */
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * Returns tthe string representation of the todo
     * @return String representation of the todo
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
