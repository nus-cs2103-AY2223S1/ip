/**
 * This class encapsulates a to-do set by the user.
 */
public class Todo extends Task {
    Todo(String content) {
        super(content);
    }

    /**
     * Returns the String representation of this to-do.
     *
     * @return A String representing this to-do.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
