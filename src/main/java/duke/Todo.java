package duke;

/**
 * Represents a task with no date information.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Return the string representation of a Todo.
     * @return "[T] {taskDescription}"
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    /**
     * Return the file representation of a Todo.
     * @return "T | {1 if done else 0} | {taskDescription}"
     */
    @Override
    public String toFileRepresentation() {
        return "T | " + super.toFileRepresentation();
    }

    /**
     * Convert string representation to Todo.
     * @param rep String representation of Todo
     * @return new Todo
     */
    public static Todo fromFileRepresentation(String rep) {
        String[] args = rep.split(" \\| ");
        boolean isDone = args[1].equals("1");
        String description = args[2];
        Todo result = new Todo(description);
        if (isDone) {
            result.markDone();
        }
        return result;
    }

}
