public class Event extends Task {

    private String time;

    public Event(String description, String time, boolean isDone) {
        super(description, "E", isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), this.time);
    }

    @Override
    public String getCsvString() {
        return String.format("event %s /at %s", super.getCsvString(), time);
    }
}
