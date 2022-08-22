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

}
