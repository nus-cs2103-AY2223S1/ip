public class Event extends Task {
    protected String dateString;

    Event(String description, String dateString) {
        super(description);
        this.dateString = dateString;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), dateString);
    }
}
