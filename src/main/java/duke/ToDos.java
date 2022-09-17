package duke;

/**
 * Represents a todo task to be added to Duke.
 */
public class ToDos extends Task {

    /**
     * Constructor of ToDos class.
     *
     * @param description Description of todo.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Returns formatted string to saved file.
     *
     * @return String of formatted todo task to saved file.
     */
    public String printSavedData() {
        return "T | " + super.printSavedData() + "\n";
    }

    /**
     * Returns formatted todo task.
     *
     * @return String of formatted todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
