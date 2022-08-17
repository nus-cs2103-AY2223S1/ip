/**
 * This class represents tasks without any date/time attached to it.
 */
public class Todo extends Task {

    /**
     * Constructor for creating a todo.
     * @param description Task description from user input.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Displays the task type of todo as T.
     * @return T.
     */
    @Override
    public String taskType() {
        return "T";
    }

}
