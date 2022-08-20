public class Event extends Task {
    protected String at;

    public Event(String description, String at, TaskType type) {
        super(description, type);
        this.at = at;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }
}
