package duke.tasks;

import duke.exceptions.DukeException;

public class Todo extends Task {

    public Todo(String description) throws DukeException {
        super(description);
    }

    @Override
    public String encode() {
        return "T | " + super.encode();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
