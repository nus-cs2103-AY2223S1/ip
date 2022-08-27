public class Event extends Task {

    protected String datetime;

    public Event(String description, String dt) {
        super(description);
        this.datetime = dt;
    }

    public String getDatetime() {
        return this.datetime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + datetime + ")";
    }
}
