public class Event extends Task{

    protected String duration;

    public Event(String description, String duration) throws MissingArgumentException {
        super("event", description, duration);
        if (duration.equals("")) {
            throw new MissingArgumentException("ERROR: event command is missing arguments.");
        }
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at:%s)", super.toString(), this.duration);
    }
}
