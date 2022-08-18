public class Event extends Task {
    private String timing;

    public Event(String description, String timing) {
        super(description);
        this.timing = timing;
    }

    public static Event createEvent(String input) {
        String eventDescription = input.split("/at ")[0];
        String event = input.split("/at ")[1];
        return new Event(eventDescription, event);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.timing + ")";
    }
}
