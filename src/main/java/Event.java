public class Event extends Task {
    private String at;

    public Event(String title, boolean done, String at) {
        super(title, done);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (at: " + at + ")";
    }
}
