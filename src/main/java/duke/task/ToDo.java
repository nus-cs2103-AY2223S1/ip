package duke.task;

/**
 * The To Do class which represents a todo task.
 *
 * @author Leong Jia Hao Daniel
 */
public class ToDo extends Task {

    /**
     * Constructs for the to Do task.
     *
     * @param description
     */
    public ToDo(String description) {
        super(description);
        assert !description.isEmpty();
    }

    public ToDo() {}

    /**
     * Reads from the input file and returns a to do task based
     * on the data in the file.
     *
     * @param string The string from the input file.
     * @return A new to do task describing the event.
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
     * Overrides the toString() method to display the task to the user.
     *
     * @return A String representing the to do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
