public class Event extends Task {
    protected String dateAndTime;

    public Event(String deadline, String dateAndTime) {
        super(deadline);
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateAndTime + ")";
    }
}
