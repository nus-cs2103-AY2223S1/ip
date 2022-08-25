package duke;

/**
 * Todo class that extends from Task
 * @author amresh A0235398R
 */
public class Todo extends Task {

    /**
     * Constructor for Todo object
     * @param description Description of Todo
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
