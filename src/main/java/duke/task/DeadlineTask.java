package duke.task;

import java.time.LocalDateTime;

import static duke.util.Parser.DATE_TIME_INPUT_FORMAT;
import static duke.util.Parser.DATE_TIME_OUTPUT_FORMAT;

public class DeadlineTask extends Task {
    private final LocalDateTime deadline;

    public DeadlineTask(String description, LocalDateTime deadline) {
        super(TaskSymbolType.D, description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.deadline.format(DATE_TIME_OUTPUT_FORMAT) + ")";
    }

    @Override
    public String toSaveString() {
        return super.toSaveString() + " | " + this.deadline.format(DATE_TIME_INPUT_FORMAT);
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        DeadlineTask that = (DeadlineTask) o;
        return deadline.equals(that.deadline);
    }
}
