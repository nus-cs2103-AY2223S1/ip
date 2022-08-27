public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    @Override
    public String getSaveFormat() {
        return "E" + " | " + (getIsDone() ? 1 : 0) + " | " + getDescription() + " | " + getAt() +
                System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
