public class Events extends Task {
    private String timing;

    public Events(String task, String timing) {
        super(task);
        this.timing = timing;
    }

    public Events (String task, String timing, boolean done) {
        super(task, done);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", timing);
    }

    @Override
    public String toSaveString() {
        return "event " + super.toSaveString() + " " + timing;
    }
}
