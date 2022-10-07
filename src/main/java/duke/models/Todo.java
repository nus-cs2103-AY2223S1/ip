package duke.models;

import duke.exceptions.DukeException;

public class Todo extends Task {
    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSave() {
        return "T" + super.toSave() + this.description;
    }

    public void snooze(String newDate) throws DukeException {
        throw new DukeException("Unable to snooze a ToDo!");
    }
}
