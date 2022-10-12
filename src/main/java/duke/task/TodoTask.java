package duke.task;

import java.time.LocalDateTime;

public class TodoTask extends Task {
    public TodoTask(String description, boolean isDone) {
        super('T', description, LocalDateTime.MAX, isDone);
        assert !description.isBlank();
    }
}
