package wagwan;

/**
* To-do is a Task with a description.
*
* @author Linus Chui
*/
public class Todo extends Task {

    /**
     * A constructor for a to-do
     *
     * @param description the description the to-do.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
