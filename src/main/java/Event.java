public class Event extends Task {

    private String eventTime;

    public Event(String taskDescription, String eventTime) {
        super(taskDescription);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        String taskStatusIndicator = "[E]" + (this.isCompleted() ? "[X] " : "[ ] ");
        return taskStatusIndicator + this.getTaskDescription() + " (at: " + this.eventTime + ")";
    }
}
