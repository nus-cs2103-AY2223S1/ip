package duke;

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
    public String getTxtString() {
        return String.format("event %s /at %s", super.getTxtString(), time);
    }
}
