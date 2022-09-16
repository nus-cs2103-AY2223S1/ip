package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.task.Task;
import duke.task.TaskType;

class DeadlineStub extends Task {
    private LocalDateTime by;
    DeadlineStub(LocalDateTime by) {
        super("", TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String getBy() {
        return this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return String.format("%s%s(by: %s)", "[D]", "[ ] Finish Assignment 1", getBy());
    }
}
