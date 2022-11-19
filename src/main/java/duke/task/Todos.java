package duke.task;

/**
 * Todos class which inherits from Task class.
 *
 * @author Kavan
 */
public class Todos extends Task {
    /**
     * Constructor for Todos class.
     *
     * @param description Description of task todo.
     */
    public Todos(String description) {
        super(description);
    }

    @Override
    public String storedTaskString() {
        return "T|" + String.valueOf(this.isDone) + "|" + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
