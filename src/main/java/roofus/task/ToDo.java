package roofus.task;

/**
 * ToDo is a type of Task that contains a description
 * and a boolean value that indicates if it is completed.
 */
public class ToDo extends Task {

    /**
     * Constructs an instance of ToDo .
     *
     * @param description Description describing the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String writeString() {
        return String.format("T | %d | %s",
                super.isDone ? 1 : 0, super.description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
