public class Event extends Task {
    protected String eventTime;

    /**
     * Constructs a new Event task.
     * @param description description of the event
     * @param eventTime the eventTime of the event.
     */
    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    /**
     * Factory method to create a Event task from an encoded string.
     *
     * @param encodedInput the encoded entry with format eventTime|description
     * @param completed the completion status of the Event task
     * @return the Event task
     */
    public static Event decode(String encodedInput, boolean completed) {
        String[] entries = encodedInput.split("\\|", 2);
        Event event = new Event(entries[1], entries[0]);
        event.setDone(completed);
        return event;
    }

    @Override
    public Type getType() {
        return Type.EVENT;
    }

    @Override
    public String encodeData() {
        return String.format("%s|%s", this.eventTime, this.description);
    }

    @Override
    public String getDisplayText() {
        return String.format("%s (at: %s)", description, eventTime);
    }
}
