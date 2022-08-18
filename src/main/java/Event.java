public class Event extends Task {
    protected String dateTime;

    public Event(String dateTime, String description) {
        super(description);
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return " (at: " + this.dateTime + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + this.getDateTime();
    }
}