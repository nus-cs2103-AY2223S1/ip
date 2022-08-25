public class Event extends Task {

    protected String time;

    public Event(String description, String time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
