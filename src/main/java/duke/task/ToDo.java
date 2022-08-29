package duke.task;

import duke.exception.EmptyDescException;

public class ToDo extends Task {
    public ToDo(String desc) throws EmptyDescException {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSave() {
        return "T | " + (isDone ? "1" : "0") + " |" + desc;
    }
}
