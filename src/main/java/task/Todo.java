package task;

/**
 *  A class which encapsulates the todo type of task.
 *  @author  Chen Guanzhou
 *  @version v2
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Attempting to postpone a todo task.
     * @return Message that the todo task cannot be postponed.
     */
    public String snooze() {
        return "ToDo tasks do not have a deadline!";
    }

    /**
     * String representation of a todo object.
     * @return The string representing the object with the state of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}