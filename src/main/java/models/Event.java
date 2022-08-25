package models;

public class Event extends Task {

    protected String at;
    protected DateFormatter dateFormatter;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.dateFormatter = new DateFormatter(at);
    }
    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", super.toString(), this.dateFormatter);
    }
}