public class Event extends Task {
    /**
     * Child class of Task with a date and time
     */
    private static final String SYMBOL = "[E]";
    protected String dateTime;
    public Event(String description, String dateTime) {
        super(description, SYMBOL);
        this.dateTime = dateTime;
    }

    public String getDate() { return this.dateTime; }

    @Override
    public String getInfo() {
        return (super.getInfo() + "--" + getDate());
    }

    @Override
    public String toString() {
        return SYMBOL + super.toString() + " (at: " + dateTime + ")";
    }
}

