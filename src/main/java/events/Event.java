package events;
public class Event extends Task {
    private final String dateTime;

    public Event(String text, String dateTime) {
        super(text);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", super.toString(), this.dateTime);
    }
}
