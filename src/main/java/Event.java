public class Event extends TimedTask {
    public Event(String description, String rawDateTime) throws DukeException {
        super(description, rawDateTime);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), getFormattedTime());
    }

    @Override
    public String getSaveFormat() {
        return String.format("E | %s | %s", super.getSaveFormat(), getFormattedTime());
    }
}
