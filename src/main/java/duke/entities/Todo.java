package duke.entities;

import duke.exceptions.DukeException;

public class Todo extends Task {

    public Todo(String desc) throws DukeException {
        super(desc);
    }

    @Override
    public String toString() {
        String marker = isDone() ? "[X] " : "[ ] ";
        return "[T]" + marker + getDescription();
    }
}