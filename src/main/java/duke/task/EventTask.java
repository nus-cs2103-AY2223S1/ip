package duke.task;

import java.time.LocalDateTime;

import duke.Parser;

public class EventTask extends Task {

    public EventTask(String description, LocalDateTime timeAt, boolean isDone) {
        super('E', description, timeAt, isDone);
        assert !description.isBlank();
        assert timeAt != null;
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(),
                super.time.format(Parser.DATETIME_OUTPUT_FORMAT));
    }
}
