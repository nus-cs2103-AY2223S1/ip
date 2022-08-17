public class Event extends Task {

    private String endTime;

    public Event(String taskName, String endTime) {
        super(taskName);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.endTime + ")";
    }
}
