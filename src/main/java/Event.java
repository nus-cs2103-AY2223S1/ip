public class Event extends Task {
    private String timing;
    public Event(String description, String timing) {
        super(description);
        this.timing = timing;
    }
    @Override
    public String getType() {
        return "E";
    }
    @Override
    public String getDescription() {
        return super.getDescription() + "(at: " + this.timing + ")";
    }
}
