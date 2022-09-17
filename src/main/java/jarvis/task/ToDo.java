package jarvis.task;

/**
 *  Represents a ToDo which is a subclass of Task.
 *
 */
public class ToDo extends Task{

    /**
     * Returns a new Todo Object with the given description.
     *
     * @param description Description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns String description of Deadline in the following format:
     * [T] *ToDo Description*
     * @return String description of ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
