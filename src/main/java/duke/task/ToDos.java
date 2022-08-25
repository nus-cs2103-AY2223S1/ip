package duke.task;

import java.time.LocalDateTime;

/**
 * Represents an ToDos task.
 */
public class ToDos extends Task {

    private static final String ID = "[T]";

    public ToDos(String detail, boolean isDone) {
        super(detail, isDone);
    }
    public ToDos(String detail) {
        super(detail);
    }

    @Override
    public LocalDateTime getTime() {
        return LocalDateTime.now();
    }
    @Override
    public String getId() {
        return ID;
    }
    @Override
    public Task markDone() {
        return new ToDos(super.getDetail(), true);
    }

    @Override
    public Task unmarkDone() {
        return new ToDos(super.getDetail(), false);
    }

    @Override
    public String toString() {
        return ID + super.toString();
    }
}
