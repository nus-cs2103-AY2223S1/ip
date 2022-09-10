package duke.task;

/** This class encapsulates a Todo tasks without a deadline */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDate() {
        return "";
    }

    /**
     * Returns String representation of the to-do task.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
