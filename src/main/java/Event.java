public class Event extends Task{
    protected String timing;

    public Event(String description, String timing) {
        super(description);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "]" +  super.toString() + " (at: " + timing + ")";
    }
}
