public class Event extends Task {

    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public Event(String description, String time, boolean done) {
        super(description);
        this.time = time;
        this.isDone = done;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }

    @Override
    public String save() {
        return "E | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.time;
    }
}