public class Event extends Task {
    private String dateTime;

    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public String getTaskIcon() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (at: %s)", this.getTaskIcon(), super.toString(), this.dateTime);
    }
}
