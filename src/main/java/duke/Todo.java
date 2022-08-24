package duke;

/**
 *  A class which encapsulates the todo type of task.
 *  @author  Chen Guanzhou
 *  @version v1
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
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