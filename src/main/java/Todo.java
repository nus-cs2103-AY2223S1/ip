/** Todo class to represent a todo.
 * @author Silas Tay A0233425M
 */
public class Todo extends Task {
    /**
     * Constructor for Todo task.
     * @param description Description for todo
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns String representation of Todo
     * @return String representation of Todo
     */
    @Override
    public String toString() {
        String completionString = this.isDone ? "[T][x]" : "[T][ ]";
        return completionString + " " + this.description;
    }
}
