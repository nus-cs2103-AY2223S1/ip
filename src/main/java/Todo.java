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
        this.type = "[T]";
    }

    /**
     * Returns String representation of Todo
     * @return String representation of Todo
     */
    @Override
    public String toString() {
        String completionString = this.type + (this.isDone ? "[x]" : "[ ]");
        return completionString + " " + this.description;
    }
}
