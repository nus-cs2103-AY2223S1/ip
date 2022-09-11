package duke.task;

import static duke.parser.Parser.DATE_TIME_INPUT_FORMAT;
import static duke.parser.Parser.DATE_TIME_OUTPUT_FORMAT;

import java.time.LocalDateTime;

/**
 * Encapsulates a task with a deadline.
 */
public class DeadlineTask extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructs a new {@code DeadlineTask} with given description and deadline.
     *
     * @param description The description of the task.
     * @param deadline The deadline of the task.
     */
    public DeadlineTask(String description, LocalDateTime deadline) {
        super(TaskSymbolType.D, description);
        this.deadline = deadline;
    }

    /**
     * Returns the string representation of this {@code DeadlineTask} for display.
     *
     * @return The string representation of this {@code DeadlineTask} for display.
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.deadline.format(DATE_TIME_OUTPUT_FORMAT));
    }

    @Override
    public String toSaveString() {
        return String.format("%s | %s", super.toSaveString(), this.deadline.format(DATE_TIME_INPUT_FORMAT));
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        DeadlineTask that = (DeadlineTask) o;
        return deadline.equals(that.deadline);
    }
}
