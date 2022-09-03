package duke.task;

import java.time.LocalDateTime;

import duke.exception.DukeException;

/**
 * Represents an Todo Task that can be described and marked as done.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String saveText() {
        return String.format("%d todo %s", this.isDone ? 1 : 0, this.description);
    }

    @Override
    public LocalDateTime getTime() {
        return null;
    }

    @Override
    public void setTime(LocalDateTime time) throws DukeException {
        throw new DukeException("You can't snooze a Todo task");
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
