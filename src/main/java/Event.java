public class Event extends Task {

    protected String due;
    private final String commandWord;

    public Event (String description, String due, String commandWord) {
        super(description);
        this.due = due;
        this.commandWord = commandWord;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + this.commandWord + ": " + this.due + ")";
    }
}
