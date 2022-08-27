public class Event extends ScheduleTask {

    public Event(String description, String at) throws UnexpectedDateTimeFormatException {
        super(description, at);
    }

    public Event(String description, String at, boolean done) {
        super(description, at, done);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + showDateTime() + ")";
    }
}
