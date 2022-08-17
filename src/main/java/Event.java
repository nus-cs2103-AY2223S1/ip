/*
This class encapsulates the idea of a event
 */
public class Event extends Task {
    private String duration;

    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon() + " " + super.toString() + "(at: " + this.duration + ")";
    }
}