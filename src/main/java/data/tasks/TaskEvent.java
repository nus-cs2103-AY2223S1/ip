package data.tasks;

public class TaskEvent extends Task {

    public static final String timingMarker = "/at";
    private final String timing;

    public TaskEvent(String title, String timing) {
        super(title);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.timing);
    }
}
