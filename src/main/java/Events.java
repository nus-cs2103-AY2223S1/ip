public class Events extends Task {
    private String timing;

    public Events(String task, String timing) {
        super(task);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", timing);
    }
}
