class Event extends Task {
    private String period;
    private static final String PREFIX = "at ";
    private static final String SPLIT = "/at ";

    Event(String description, String period) {
        super(description);
        this.period = period;
    }

    static Event createEvent(ParsedData data) throws DukeException {
        if (data.description.length() == 0)
            throw new EmptyDescriptionError("event");

        if (data.additionalInfo.length() == 0 || !data.additionalInfo.startsWith(PREFIX))
            throw new EmptyTimeException("event", SPLIT);

        return new Event(data.description, data.additionalInfo.substring(3));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), period);
    }
}
