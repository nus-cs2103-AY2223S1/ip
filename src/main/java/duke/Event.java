package duke;

public class Event extends Task {
    private String duration;

    public Event(String name, String duration) {
        super(name);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.duration + ")";
    }

    public String getDuration() {
        return this.duration;
    }
}
