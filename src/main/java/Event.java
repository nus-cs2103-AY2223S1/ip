public class Event extends Item{
    private final String itemType = "[E]";
    private final String eventTime;

    public Event(String item, String eventTime) {
        super(item);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return this.itemType + super.toString() + " (at: " + this.eventTime + ")";
    }
}
