/**
 * A task to be done.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class Todo extends Task {
    /**
     * A basic constructor to instantiate the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Method that returns the description of the Todo.
     *
     * @return The description of the Todo along with its status.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
