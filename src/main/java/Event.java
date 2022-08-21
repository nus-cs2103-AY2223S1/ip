public class Event extends Task {

    protected String at;

    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}