package duke;

public class Event extends Task {

    protected Event(String name, String date) {
        super(name, date);
        type = "E";
    }

    @Override
    public String stringType() {
        return "event";
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), getDate());
    }
}
