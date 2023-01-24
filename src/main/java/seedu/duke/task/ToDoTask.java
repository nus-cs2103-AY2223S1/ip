package seedu.duke.task;

/**
 * Basic task.
 */
public class ToDoTask extends Task {
    private static final String TYPE = "[T]";

    public ToDoTask(String name) {
        super(name);
    }

    /**
     * Constructor only for loading task list.
     * @param name
     * @param isDone
     */
    public ToDoTask(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String getType() {
        return TYPE;
    }
    /**
     * Returns string in form: [E] task name
     * @return
     */
    @Override
    public String toString() {
        return TYPE + super.toString();
    }
}
