package duke;

import java.time.LocalDateTime;

/**
 * Represents a to-do type task
 * @author Reuben Chay
 */
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

    /**
     * Returns a string representing this class' information
     * @return string including task type, done/undone and task name
     */
    @Override
    public String toString() {
        String out = "[T]";
        out += super.toString();
        return out;
    }
}
