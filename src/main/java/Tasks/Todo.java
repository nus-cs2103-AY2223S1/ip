/**
 * Creates a new todo task from the todo command
 */
package Tasks;

public class Todo extends Task {
    public Todo(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
