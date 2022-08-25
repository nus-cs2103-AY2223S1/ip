public class Event extends Task {
    protected String dateTime;

    public Event(String dateTime, String description) {
        super(description, "E");
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return " (at: " + this.dateTime + ")";
    }

    public String printText() {
        return super.printText() + " | " + this.dateTime;
    }
    @Override
    public String toString() {
        return "[" + this.getCode() + "]" + super.toString() + this.getDateTime();
    }
}