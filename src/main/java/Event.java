public class Event extends Task {
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }

    @Override
    public String toWrite() {
        return String.format("E/%s/%s/%s", (isDone ? "1" : "0"), description.trim(), time.trim());
    }
}
