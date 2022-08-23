package duke.task;

import static duke.util.Parser.DATE_TIME_INPUT_FORMAT;
import static duke.util.Parser.DATE_TIME_OUTPUT_FORMAT;

import java.time.LocalDateTime;

public class EventTask extends Task {
    private final LocalDateTime time;

    public EventTask(String description, LocalDateTime time) {
        super(TaskSymbolType.E, description);
        this.time = time;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.time.format(DATE_TIME_OUTPUT_FORMAT) + ")";
    }

    @Override
    public String toSaveString() {
        return super.toSaveString() + " | " + this.time.format(DATE_TIME_INPUT_FORMAT);
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        EventTask that = (EventTask) o;
        return time.equals(that.time);
    }
}
