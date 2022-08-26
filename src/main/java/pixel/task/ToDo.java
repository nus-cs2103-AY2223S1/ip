package pixel.task;

import java.util.Objects;

public class ToDo extends Task {

    public static final String TAG = "T";

    public ToDo (String description, String due, String commandWord) {
        super(description, due, commandWord);
    }

    @Override
    public String toString() {
        String end = Objects.equals(this.commandWord, "")
            ? this.commandWord
            : " (" + this.commandWord + ": " + this.due + ")";

        return "[" + this.TAG + "]" + super.toString() + end;
    }
}
