public class Event extends Task {
    private String at;

    public Event(String desc, String at) throws MissingDescriptionException, MissingTimeException {
        super(desc);
        if (desc.isBlank()) {
            throw new MissingDescriptionException(Duke.commandGuide("event", Command.EVENT));
        } else if (at.isBlank()) {
            throw new MissingTimeException(Duke.commandGuide("event", Command.EVENT));
        }
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
