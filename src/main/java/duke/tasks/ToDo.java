package duke.tasks;

/**
 * Represents a ToDo in Duke.
 * 
 * @author Ramanathan Kumarappan
 */
public class ToDo extends Task {
    /**
     * Constructor for ToDo.
     * @param description - The description of the ToDo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Rebuilds the ToDo from a todo save string.
     * 
     * @param saveString - The save string containing todo information.
     * @return A ToDo task.
     */
    public static ToDo taskFromSave(String saveString) {
        String[] tokens = saveString.split(" \\| ");
        ToDo todo = new ToDo(tokens[2]);
        if (tokens[1].equals("1")) {
            todo.markDone();
        }
        return todo;
    }

    /**
     * Returns the ToDo in a save string format.
     *
     * @return The ToDo in a save string format.
     */
    @Override
    public String saveString() {
        return "T | " + super.saveString();
    }

    /**
     * Returns a string representation of the ToDo.
     *
     * @return The string representation of the ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
