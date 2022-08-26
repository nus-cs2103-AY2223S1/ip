package pixel.task;

import java.util.Objects;

public class Deadline extends Task {

    public static final String TAG = "D";

    public Deadline (String description, String due, String commandWord) {
        super(description, due, commandWord);
    }

    @Override
    public String toString() {
        String end = Objects.equals(this.commandWord, "")
            ? this.commandWord
            : " (" + this.commandWord + ": " + this.due + ")";

        return "[" + TAG + "]" + super.toString() + end;
    }
}