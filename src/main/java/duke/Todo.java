package duke;

/**
 * Represents a to-do, which is a specific type of task.
 *
 * @author Liu Han
 */
public class Todo extends Task {

    /**
     * To-do Constructor
     * @param description Description of the to-do.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Prints the to-do when list is called by the user
     * @return String in the format <b>[T][isDone] description</b>.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Saves the to-do into a string and writes into a .txt file.
     * @return String in the format <b>T | 1/0 | description</b>.
     */
    @Override
    public String toSave() {
        return "T | " + (this.isDone ? "1 | " : "0 | ") + this.getDescription() + "\n";
    }
}