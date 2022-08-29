package ekud.task;

public class Event extends Task {
    private final String at;

    /**
     * Constructor that instantiates an instance of Event.
     * 
     * @param description Description of task.
     * @param at Time of event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileFormat() {
        int isDone = this.getStatusIcon() == "X" ? 1 : 0;
        return String.format("E|%d|%s|%s", isDone, this.getDescription(), this.at);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.at);
    }
}
