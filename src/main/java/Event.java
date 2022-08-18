package main.java;

public class Event extends Task {

    protected String at;

    public Event(String action, String at) {
        super(action);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (at: " + at + ")";
    }
}
