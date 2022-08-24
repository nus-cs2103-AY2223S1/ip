package TaskList;

import TaskList.Task;

public class Event extends Task {

    protected String at;

    public Event(String detail, String at) {
        super(detail);
        this.at = at;
    }

    public Event(String detail, boolean isDone, String at) {
        super(detail, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String storedData() {
        return "E" + "|" + super.storedData() + "|" + at;
    }
}
