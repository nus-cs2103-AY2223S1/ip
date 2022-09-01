public class Event extends Task {
    protected String time;

    Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public String getDue() {
        return this.time;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
