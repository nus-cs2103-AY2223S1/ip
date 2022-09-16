package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.task.Task;
import duke.task.TaskType;



class EventStub extends Task {
    private LocalDateTime at;
    EventStub(LocalDateTime at) {
        super("", TaskType.EVENT);
        this.at = at;
    }

    @Override
    public String getBy() {
        return this.at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return String.format("%s%s(at: %s)", "[E]", "[ ] Dinner date", getBy());
    }
}
