package duke.task;

public class Event extends Task {
    private final String eventAt;

    public Event(String description, String eventAt) {
        super(description);
        this.eventAt = eventAt;
    }

    public Event(String description, String eventAt, boolean isDone) {
        super(description, isDone);
        this.eventAt = eventAt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String convertToFileFormat() {
        return String.format("event | %s | %s | %b", super.description, eventAt, super.isDone);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), eventAt);
    }
}
