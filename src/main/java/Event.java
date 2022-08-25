public class Event extends ScheduleTask {

    public Event(String description, String at) {
        super(description, at);
    }

    public Event(String description, String at, boolean done) {
        super(description, done);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + showDateTime() + ")";
    }
}
