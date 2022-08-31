package tasks;

public class Event extends Task {

    private final String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + time + ")";
    }

    @Override
    public String toDataString() {
        return String.format("[E] | %d | %s | %s",
                isMarked() ? 1 : 0,
                getName(),
                time);
    }
}
