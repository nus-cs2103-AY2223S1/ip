package duke.task;

import duke.Parser;

import java.time.LocalDateTime;

public class EventTask extends Task {
    private final LocalDateTime time;

    public EventTask(String description, LocalDateTime time, boolean isDone) {
        super( 'E', description, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(),
                time.format(Parser.DATETIME_OUTPUT_FORMAT));
    }

    @Override
    public String toData() {
        return String.format("%s | %s", super.toData(),
                time.format(Parser.DATETIME_INPUT_FORMAT));
    }
}