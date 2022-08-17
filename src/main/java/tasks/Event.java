package tasks;

import exceptions.DukeException;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public void checkCommandValidity(String value, String flag, String options) throws DukeException {
        if (flag == null || !(flag.equals("at"))) {
            throw new DukeException("Correct usage: event project /at 1st Jan");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }
}
