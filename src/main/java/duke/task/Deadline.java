package duke.task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime deadlineTiming;

    public Deadline(String description, LocalDateTime deadlineTiming) {
        super(description);
        this.deadlineTiming = deadlineTiming;
        this.taskType = TaskType.DEADLINE;
    }

    @Override
    public String encode() {
        return super.encode() + " | " + this.deadlineTiming;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + deadlineTiming.format(Task.DATE_TIME_DISPLAY_FORMATTER) + ")";
    }
}
