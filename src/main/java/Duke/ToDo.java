package duke;

import java.time.LocalDateTime;

public class ToDo extends Task {

    ToDo(String name) {
        super(name);
    }

    @Override
    LocalDateTime getDateTime() {
        return null;
    }

    @Override
    boolean isToDo() {
        return true;
    }

    @Override
    public String toString() {
        String out = "[T]";
        out += super.toString();
        return out;
    }
}
