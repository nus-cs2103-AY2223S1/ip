package duke;

/**
 * ATodo class which is a subclass of Task
 */
public class Todo extends Task {
    /**
     * Constructor for Todo Class
     * @param description Description of the todo
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a String to represent the todo task during listing
     * @return String to be displayed when list
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Creates a String to be saved in the file
     * @return String to be displayed in the file
     */
    @Override
    public String savedString() {
        return "T | " + (this.isDone ? "1 | " : "0 | ") + this.description;
    }
}