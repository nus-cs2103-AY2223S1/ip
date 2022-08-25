public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getTime() {
        return at;
    }

    public String getStorageString() {
        return "E" + "|" + (this.isDone ? "1" : "0") + "|" + this.getDescription() + "|" + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
