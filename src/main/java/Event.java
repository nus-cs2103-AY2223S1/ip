public class Event extends Task {
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", isDone ? "X" : " ", description, time);
    }
}
