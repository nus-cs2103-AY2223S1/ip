public class ToDo extends Task {

    protected String due;
    private final String commandWord;

    public ToDo (String description, String due, String commandWord) {
        super(description);
        this.due = due;
        this.commandWord = commandWord;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " (" + this.commandWord + ": " + due + ")";
    }
}
