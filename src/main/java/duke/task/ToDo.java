package duke.task;


/**
 * Represents a toDo task
 *
 * @author benjytan45678
 * @version 0.1
 */
public class ToDo extends Task {
    private String name;

    /**
     * Instantiates a toDo task with specified name.
     *
     * @param name name of the toDo task
     */
    public ToDo(String name) {
        super(name);
        this.name = name;
    }

    /**
     * Creates a String representation of the toDo task
     *
     */
    @Override
    public String toString() {

        return "[T]" + super.toString();
    }

    /**
     * Creates a simplified version of the toDo task to be stored in local file
     *
     */
    public String parse() {
        if (this.getHasCompleted()) {
            return "T#1#" + this.name;
        } else {
            return "T#0#" + this.name;
        }

    }
}
