public class Event extends Task {
    private String time;
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public Event(Boolean isDone, String description, String time) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String formatTask() {
        return String.format("[E] [%s] %s (at: %s)", this.getStatusIcon(), this.description, time );
    }
    public String formatTaskString() {
        return String.format("E|%s|%s|%s", this.isDone, this.description, this.time);
    }

}
