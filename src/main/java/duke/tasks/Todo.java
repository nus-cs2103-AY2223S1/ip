package duke.tasks;

/**
 * Represents a todo item in a task list
 */
public class Todo extends Task {

    /**
     * Constructor Method of Todo class
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor Method of Todo class
     * @param isDone
     * @param description
     */
    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Converts Todo Object to String
     * @return String representation of Todo Object
     */
    @Override
    public String toString() {
        String s = super.toString();
        return "[T]" + s;
    }

    public String toShortString() {
        return toString();
    }

    /**
     * Converts Todo Object into a string to be stored in a file
     * @return condensed String representation of Todo Object
     */
    public String toFile() {
        String s = super.toFile();
        return "T," + s;
    }
}
