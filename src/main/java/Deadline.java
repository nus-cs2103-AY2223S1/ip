public class Deadline extends Task {

    protected String due;
    private final String commandWord;

    public Deadline(String description, String due, String commandWord) {
        super(description);
        this.due = due;
        this.commandWord = commandWord;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + this.commandWord + ": " + due + ")";
    }
}
