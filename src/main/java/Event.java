class Events extends Task {

    private String eventTime;

    Events(String description, String eventTime) {

        super(description, false);
        this.eventTime = eventTime;
    }

    Events(String description, boolean isDone, String eventTime) {

        super(description, isDone);
        this.eventTime = eventTime;

    }

    @Override
    public String toString() {

        return "[E]" + super.toString() + " (at: " + eventTime + ")";
    }

}
