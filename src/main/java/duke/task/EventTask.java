package duke.task;

import java.time.LocalDateTime;

import duke.Parser;

public class EventTask extends Task {
    private final LocalDateTime timeAt;

    public EventTask(String description, LocalDateTime timeAt, boolean isDone) {
        super('E', description, isDone);
        this.timeAt = timeAt;
        assert !description.isBlank();
        assert timeAt != null;
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(),
                timeAt.format(Parser.DATETIME_OUTPUT_FORMAT));
    }

    @Override
    public String toData() {
        return String.format("%s | %s", super.toData(),
                timeAt.format(Parser.DATETIME_INPUT_FORMAT));
    }
}
