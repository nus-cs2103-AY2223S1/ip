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
    public String getTiming() {
        return this.timing;
    }
}
