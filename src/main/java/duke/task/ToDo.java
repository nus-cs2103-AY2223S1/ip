package duke.task;

/**
 * The class represents a todo task.
 *
 * @author Bryan Ng Zi Hao
 */
public class ToDo extends Task {

    /**
     * Constructor for Todo.
     *
     * @param description Describes the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Reads the input file and returns a todo task based on
     * the data stored in the file.
     *
     * @param data The extracted data from the file.
     * @return A new todo that describes the task.
     */
    public static ToDo parseFile(String data) {
        String[] details = data.split(" \\| ");
        ToDo todo = new ToDo(details[2]);
        if (details[1].equals("1")) {
            todo.markAsDone();
        }
        return todo;
    }

    /**
     * Changes the format of the string description such that
     * it fits with the file's format.
     *
     * @return A new string that fits the file's format.
     */
    @Override
    public String toDataFormat() {
        String completed = "";
        if (this.isDone) {
            completed = "1";
        } else {
            completed = "0";
        }
        return "T | " + completed + " | " + this.getDescription();
    }

    /**
     * Override the toString() method to display the task.
     *
     * @return A String representing the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
