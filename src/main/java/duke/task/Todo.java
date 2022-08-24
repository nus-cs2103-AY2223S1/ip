package duke.task;

/**
 * Represents To-do which is a specific type of task.
 */
public class Todo extends Task {

    /**
     * Creates an instance of a to-do task.
     *
     * @param desc Description of to do
     */
    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String getDescription() {
        return super.description;
    }

    /**
     * Returns string representation of a to-do.
     *
     * @return String representation of a to-do
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Return shorthand of deadline task type.
     *
     * @return T for to-do
     */
    @Override
    public String getTaskType() {
        return "T";
    }
}
