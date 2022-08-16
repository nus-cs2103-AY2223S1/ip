public class Event extends Task {
    private final String at;

    Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getTaskIcon() {
        return "E";
    }

    @Override
    public String toString() {
        return "[" + this.getTaskIcon() + "]" + super.toString() + " (at: " + this.at + ")";
    }
}
