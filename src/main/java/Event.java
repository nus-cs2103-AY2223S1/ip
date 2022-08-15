public class Event extends Task {
    private static final String SYMBOL = "[E]";
    protected String dateTime;
    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return SYMBOL + super.toString() + "(at:" + dateTime + ")";
    }
}

