public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        super.print(this);
        updateTasks();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
