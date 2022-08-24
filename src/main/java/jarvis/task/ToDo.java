package jarvis.task;

/**
 * ToDo --- todo task.
 */
public class ToDo extends Task {
    /**
     * Constructor.
     *
     * @param description description of task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
