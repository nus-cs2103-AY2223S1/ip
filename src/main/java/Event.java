public class Event extends Task {
    private String datetime;

    public Event(String description, String datetime) {
        super(description);
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + datetime + ")";
    }
}