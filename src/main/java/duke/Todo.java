package duke;

/**
 * A class that creates an object of type Todo.
 *
 * @author Safwan A0235287X
 */
public class Todo extends Task{

    protected String by;

    /**
     * Constructor that creates a Todo object.
     * @param description Details of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Method that overrides Java.toString() method to convert the Todo
     * task as a string.
     * @return A string of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

