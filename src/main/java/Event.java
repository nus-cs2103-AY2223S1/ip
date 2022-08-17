public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        String completionString = this.isDone ? "[E][x]" : "[E][ ]";
        return completionString + " " + this.description + " (at: " + this.at + ")";
    }
}
