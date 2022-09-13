/**
 * Project Duke CS2103
 * Done by Hong Jin.
 */
package duke.task;

/**
 * class Todo to handle Todo Task.
 */
public class Todo extends Task {

    /**
     * public constructor for Todo.
     * @param task
     */
    public Todo(String task) {
        super(task);
    }

    /**
     * class method to return String representation of Todo Task.
     * @return String.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
