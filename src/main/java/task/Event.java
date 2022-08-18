package task;

public class Event extends Task {
    String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    protected String getTypeIndicator() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), at);
    }
}
