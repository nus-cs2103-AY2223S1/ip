public class Event extends Task {
    String at;

    public Event(String title, String at) {
        super(title);
        this.at = at;
    }

    public Event(String title, String at, Boolean isDone) {
        super(title, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + at + ")";
    }

    public String toSaveString() {
        return "E|" + super.toSaveString() + "|" + at;
    }
}
