public class Event extends Task {
    protected String at;

    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = at;
        if (description.isBlank()) {
            throw new DukeException("Take me seriouslyy :( What do you want to do?");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
