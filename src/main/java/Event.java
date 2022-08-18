public class Event extends Task {
    private String specTime;

    public Event(String description, String specTime) {
        super(description);
        this.specTime = specTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.specTime + ")";
    }
}