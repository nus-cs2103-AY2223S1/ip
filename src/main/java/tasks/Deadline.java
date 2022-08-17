package tasks;

import exceptions.DukeException;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public void checkCommandValidity(String value, String flag, String options) throws DukeException {
        if (flag == null || !(flag.equals("by"))) {
            throw new DukeException("Correct usage: deadline project /by 1st Jan");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
