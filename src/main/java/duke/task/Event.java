package duke.task;

public class Event extends Task {
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public String getOutput() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, description, time);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
