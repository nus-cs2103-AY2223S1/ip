package Sakura;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String stringifyTask() {
        return String.format("E|%d|%s|%s", this.isDone ? 1 : 0, this.description, this.at);
    }

    @Override
    public String toString() {
        return "\u001B[35m(EVENT)\u001B[0m" + super.toString() + " (at: " + at + ")";
    }
}
