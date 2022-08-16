class Event extends Task {
    private String period;

    Event(String description, String period) {
        super(description);
        this.period = period;
    }

    static Event createEvent(ParsedData data) {
        /*
         * TODO
         * Add error checking here
         */

        return new Event(data.description, data.additionalInfo.substring(3));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), period);
    }
}
