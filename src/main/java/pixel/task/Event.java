package pixel.task;

import java.util.Objects;

public class Event extends Task {

    public final String tag = "E";

    public Event (String description, String due, String commandWord) {
        super(description, due, commandWord);
    }

    @Override
    public String toString() {
        String end = Objects.equals(this.commandWord, "")
            ? this.commandWord
            : " (" + this.commandWord + ": " + this.due + ")";
        return "[" + this.tag + "]" + super.toString() + end;
    }
}
