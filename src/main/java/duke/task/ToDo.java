package duke.task;

import static duke.common.Messages.TODO_ID;

import java.time.LocalDateTime;

/**
 * Represents an ToDo task.
 */
public class ToDo extends Task {

    public ToDo(String detail, boolean isDone) {
        super(detail, isDone);
    }
    public ToDo(String detail) {
        super(detail);
    }

    @Override
    public LocalDateTime getTime() {
        assert false : "Todo task time is not callable in code.";
        return LocalDateTime.now();
    }
    @Override
    public String getId() {
        return TODO_ID;
    }
    @Override
    public Task markDone() {
        return new ToDo(super.getDetail(), true);
    }

    @Override
    public Task unmarkDone() {
        return new ToDo(super.getDetail(), false);
    }

    @Override
    public String toString() {
        return TODO_ID + super.toString();
    }
}
