public class Event extends Task {
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), time);
    }

    @Override
    public String getSaveFormat() {
        return String.format("E | %s | %s", super.getSaveFormat(), time);
    }
}
