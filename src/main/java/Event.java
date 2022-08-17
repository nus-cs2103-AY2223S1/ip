public class Event extends Task {
    private String timing;

    public Event(String taskDescription, String timing) {
        super(taskDescription);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), timing);
    }
}
