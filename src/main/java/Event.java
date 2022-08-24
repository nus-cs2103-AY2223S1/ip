public class Event extends Task {

    public static final String EVENT_REP = "E";

    protected String time;

    public Event(String content, String time) {
        super(content);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[" + EVENT_REP + "]" + super.toString() + " (at: " + this.time + ")";
    }

    @Override
    public String toStorage() {
        return EVENT_REP + super.toStorage() + Task.SEPARATOR + this.time;
    }
}
