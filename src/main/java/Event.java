public class Event extends Task {

    private String timing;

    public Event(String description, String timing) {
        super(description);
        this.timing = timing;
    }

    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: " + this.timing + ")", this.getTaskType(), super.toString());
    }
}
