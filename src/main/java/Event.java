public class Event extends Task {
    private String time;
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "  [E] [" + this.getStatusIcon() + "] " + this.description + " (at: " + this.time + ")";
    }
}
