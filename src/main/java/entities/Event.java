package entities;

public class Event extends Task{
    private final String time;
    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time + ")";
    }
}
