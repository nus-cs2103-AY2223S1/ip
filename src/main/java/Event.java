public class Event extends Task {
    private String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[" + TaskType.E + "]" + super.toString() + " (at: " + this.date + ")";
    }

    @Override
    public String toCommand() {
        return TaskType.E + " | " + super.toCommand() + " /at " + this.date;
    }
}
