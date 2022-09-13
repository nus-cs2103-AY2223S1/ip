package duke;

/**
 * Encapsulate Todo which is-a Task.
 */
public class ToDo extends Task {

    /**
     * Constructs an instance of Todo which inherits from Task.
     *
     * @param description Todo's description.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public void updateDate(String updatedDate) throws DukeException {
        throw new DukeException("This task does not have a date to be updated nya.");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorageString() {
        return "T" + super.toStorageString();
    }

}
