package duke;

/**
 * Represents a todo task.
 */
public class ToDo extends Task {

    /**
     * Class constructor.
     *
     * @param description Description of todo task.
     */
    public ToDo(String description) {
        super(description);
    }


    @Override
    public String saveString() {
        return "T | " + (this.isDone ? "1 | " : "0 | ") + this.description;
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
