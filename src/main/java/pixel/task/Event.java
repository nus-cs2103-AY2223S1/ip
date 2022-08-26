package pixel.task;

import java.util.Objects;

public class Event extends Task {

    public static final String TAG = "E";

    public Event (String description, String due, String commandWord) {
        super(description, due, commandWord);
    }

    @Override
    public String toString() {
        String end = Objects.equals(this.commandWord, "")
            ? this.commandWord
            : " (" + this.commandWord + ": " + this.due + ")";

        return "[" + Event.TAG + "]" + super.toString() + end;
    }
}
