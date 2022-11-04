package duke.task;

import duke.DukeException;

public class Todo extends Task {

    public Todo(String desc) throws DukeException {
        super(desc);
    }

    public Todo(String desc, boolean isDone) throws DukeException {
        super(desc, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorageString() {
        return String.format("%s / T", super.toStorageString());
    }

}
