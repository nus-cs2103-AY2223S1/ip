public class EventTask extends Task {

    private final String time;

    public EventTask(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + " " + description + " (at: " + time +
               ")";
    }
}
