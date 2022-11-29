package duke.utils;

import java.time.LocalDateTime;
import java.util.Optional;

import duke.tasks.Task;

/**
 * Represents a stub for Task.
 */
public class TaskStub extends Task {
    public TaskStub(String description) {
        super(description, false);
    }

    @Override
    public String getTaskWord() {
        return "TaskStub";
    }

    @Override
    public Optional<LocalDateTime> getTime() {
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "taskstub";
    }
}
