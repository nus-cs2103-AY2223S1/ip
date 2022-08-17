public class Event extends Task {
    private String timing;

    public Event(String taskDescription, boolean completedTask, String timing) {
        super(taskDescription, completedTask);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), timing);
    }
}
