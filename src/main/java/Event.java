public class Event extends Task {
    String at;

    public Event(String title, String at) {
        super(title);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + at + ")";
    }
}
