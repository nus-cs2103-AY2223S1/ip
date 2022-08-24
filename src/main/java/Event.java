public class Event extends Task {
    protected String dateAndTime;

    public Event(String deadline, String dateAndTime) {
        super(deadline);
        this.dateAndTime = dateAndTime;
    }

    public Event(String deadline, String dateAndTime, boolean isDone) {
        super(deadline, isDone);
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateAndTime + ")";
    }

    public String toFileString() {
        return "E , " + super.toFileString()  + " , " + dateAndTime;
    }
}
