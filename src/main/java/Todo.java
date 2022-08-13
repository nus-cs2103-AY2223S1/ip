/**
 * Todo class that inherits from Task.
 */
public class Todo extends Task {

    /**
     * Constructor of Todo class.
     *
     * @param description a String that represents the description for the Todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a String that represents the Todo.
     *
     * @return a String that represents the Todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
