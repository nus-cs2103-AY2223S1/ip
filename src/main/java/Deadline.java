public class Deadline extends Task {

    public final String tag = "D";

    public Deadline (String description, String due, String commandWord) {
        super(description, due, commandWord);
    }

    @Override
    public String toString() {
        return "[" + this.tag + "]" + super.toString() + " (" + this.commandWord + ": " + this.due + ")";
    }
}