package duke.task;

public class Event extends Task {
    protected String eventTime;

    public Event(String description, String eventTime) {
        this.description = description;
        this.isDone = false;
        this.eventTime = eventTime;
    }

    public Event(String description, boolean isDone, String eventTime) {
        this.description = description;
        this.isDone = isDone;
        this.eventTime = eventTime;
    }

    @Override
    public String saveStringFormat() {
        return String.format("E | %d | %s | %s", this.isDone? 1 : 0, this.description, this.eventTime);
    }
    @Override
    public String toString() {
        return "[E] " + "[" + this.getStatusIcon() + "] " + this.description
                +  " (at: " + this.eventTime + ")";
    }
}
