/**
 * The Todo class contains information of a Todo task.
 * @author Sheryl-Lynn Tan (G11)
 */
public class Todo extends Task {
    /**
     * Constructor for Todo.
     * @param description of task name.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * This method returns the string of a Todo.
     */
    @Override
    public String toString() {
        return "[T] " + this.getStatusIcon() + " " + this.description ;
    }
}