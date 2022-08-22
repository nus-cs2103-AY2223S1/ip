public class Event extends Task {
    private String dateTime;

    public Event(String description, String dateTime) {
        super(description, TaskType.EVENT);
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (at: %s)", super.getTaskIcon(), super.toString(), this.getDateTime());
    }
}
