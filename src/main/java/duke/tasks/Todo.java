package duke.tasks;

import duke.exceptions.DukeException;

public class Todo extends Task {

    public Todo(String description) throws DukeException {
        super(description);
    }

    /**
     * Returns a string representation of the object suitable for storage.
     *
     * @return A storage-friendly string representation.
     */
    @Override
    public String encode() {
        return "T | " + super.encode();
    }

    /**
     * Returns a user-friendly string representation of the object.
     *
     * @return A user-friendly string representation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
