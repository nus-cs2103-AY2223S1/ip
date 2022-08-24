public class Event extends Task {
    protected String type = "E";
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString() + "(at: " + at + ")";
    }
}