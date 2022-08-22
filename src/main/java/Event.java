public class Event extends Task {
    private final String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String stringToSave() {
        return "E|" + ("X".equals(super.getStatusIcon()) ? "1|" : "0|") + super.description + "|" + this.time;
    }
    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + this.time + ")";
    }
}
