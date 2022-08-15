package duke.task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, String at) {
        super(description);
        this.at = at;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.at);
    }

    @Override
    public String saveTask() {
        return String.format("E | %s | %s", super.saveTask(), this.at);
    }
}