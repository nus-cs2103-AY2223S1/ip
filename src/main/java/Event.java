public class Event extends Task {
    protected String at;

    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = at;
        if (description.isBlank() || at.isBlank()) {
            throw new DukeException("The description of event task is missing information!");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
