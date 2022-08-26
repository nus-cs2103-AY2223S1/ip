public class Event extends Task {

    protected String at;
    protected Date date;

    public Event(String description, String at) throws DukeInvalidDateException {
        super(description);
        this.at = at;
        this.date = new Date(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }

    @Override
    public String toStringForFile() {
        return super.toStringForFile() + "event " + this.description + " /at " + this.at;
    }

}