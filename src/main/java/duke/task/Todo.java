package duke.task;

/**
 * Child class of Task without any date/time attached to it
 */
public class Todo extends Task {
    private static final String SYMBOL = "[T]";

    /**
     * Constructor for the Todo class
     *
     * @param description description of the todo
     */
    public Todo(String description) {
        super(description, SYMBOL);
    }

    /**
     * Method to return a string representation of the todo task
     * @return string representation of the todo task
     */
    @Override
    public String toString() {
        return SYMBOL + super.toString();
    }
}
