public class Event extends Task {
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    @Override
    public String toStorageFormat() {
        int done = isDone ? 1 : 0;
        String res = String.format("D | %d | %s | %s", done, taskName, at);
        return res;
    }
}