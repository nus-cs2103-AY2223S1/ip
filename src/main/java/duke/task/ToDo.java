package duke.task;

/**
 * The ToDo class which represents a todo task.
 *
 * @author Leong Jia Hao Daniel
 */
public class ToDo extends Task {

    /**
     * The constructor for the toDo task.
     *
     * @param description
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Reads from the input file and returns a todo task based
     * on the data in the file.
     *
     * @param string The string from the input file.
     * @return A new todo task describing the event.
     */
    public static ToDo parseFile(String string) {
        String[] details = string.split(" \\| ");
        ToDo todo = new ToDo(details[2]);
        if (details[1].equals("1")) {
            todo.markAsDone();
        }
        return todo;
    }

    /**
     * Overrides the toDataFormat() in task to return a String which
     * is stored in the file.
     *
     * @return The task but formatted in the way it is meant to
     *         be stored in the file.
     */
    @Override
    public String toDataFormat() {
        String completed = "0";
        if (this.getStatusIcon().equals("X")) {
            completed = "1";
        }
        return "T | " + completed + " | " + this.getDescription();
    }

    /**
     * Override the toString() method to display the task to the user.
     *
     * @return A String representing the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
