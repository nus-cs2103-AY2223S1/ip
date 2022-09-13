package duke.models;

import duke.exceptions.DukeException;

public class Event extends Task {
    protected FormatDate date;

    public Event(String description, String at) {
        super(description);
        this.date = new FormatDate(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: "+ this.date + ")";
    }

    @Override
    public String toSave() {
        return "E" + super.toSave() + this.description + "| " + this.date;
    }

    @Override
    public void snooze(String newDate) throws DukeException {
        this.date = new FormatDate(newDate);
    }
}
