/*
This class encapsulates the idea of a event
 */
public class Event extends Task {
    private String duration;

    public Event(String description, boolean status, String duration) {
        super(description, status);
        this.duration = duration;
    }

    @Override
    public String getDescription() {
        String status = super.getStatus() ? "T" : "F";
        return "E | " + status + " | " + super.toString() + " | " + duration + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon() + " " + super.toString() + "(at: " + this.duration + ")";
    }
}