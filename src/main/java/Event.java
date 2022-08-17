public class Event extends Task {
    protected String type = "[E]";
    protected String dateTime = "(at: ";
    Event(String description, String dateTime) {
        super(description);
        this.dateTime = this.dateTime + dateTime + ")";
    }

    @Override
    public String toString() {
        return type + super.toString() + dateTime;
    }
}
