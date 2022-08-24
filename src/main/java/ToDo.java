public class ToDo extends Task {

    public final String tag = "T";

    public ToDo (String description, String due, String commandWord) {
        super(description, due, commandWord);
    }

    @Override
    public String toString() {
        return "[" + this.tag + "]" + super.toString() + " (" + this.commandWord + ": " + this.due + ")";
    }
}
