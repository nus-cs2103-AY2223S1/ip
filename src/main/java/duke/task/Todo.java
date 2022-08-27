package duke.task;

import duke.utilities.DukeException;

public class Todo extends Task{

    public Todo(String description) throws DukeException {
        super(description);
        if (description.equals("")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
