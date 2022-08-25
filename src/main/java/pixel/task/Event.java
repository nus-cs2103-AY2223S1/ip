package pixel.task;

public class Event extends Task {

    public final String tag = "E";

    public Event (String description, String due, String commandWord) {
        super(description, due, commandWord);
    }

    @Override
    public String toString() {
        return "[" + this.tag + "]" + super.toString() + " (" + this.commandWord + ": " + this.due + ")";
    }
}
