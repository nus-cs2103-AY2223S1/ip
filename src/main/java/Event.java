public class Event extends Task {
    private final String at;

    Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[" + TaskType.E + "]" + super.toString() + " (at: " + this.at + ")";
    }
}
