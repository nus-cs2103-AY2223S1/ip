public class Event extends Task{
    protected String byDateTime;

    Event(String description, String byDateTime) {
        super(description);
        this.byDateTime = byDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.byDateTime +")";
    }

}
