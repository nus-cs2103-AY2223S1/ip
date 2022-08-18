public class Event extends Task {
    private static final String typeIcon = "E";
    private String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", typeIcon, super.toString(), this.time);
    }
}
