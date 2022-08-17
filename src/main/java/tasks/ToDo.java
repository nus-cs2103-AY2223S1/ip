package tasks;

import exceptions.DukeException;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public void checkCommandValidity(String value, String flag, String options) throws DukeException {
        if (!(flag == null || options == null)) {
            throw new DukeException("Correct usage: todo project");
        }
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
