package duke.models.task;

import java.time.LocalDate;

import duke.models.serializable.TaskSerializable;
import duke.utils.DukeFormatter;

/**
 * Encapsulates a {@link Task} that needs to be done before a specific date.
 *
 * @author Emily Ong Hui Qi
 */

public class Deadline extends Task {
    private static final TaskType taskType = TaskType.DEADLINE;
    protected LocalDate deadline;

    /**
     * Initializes the Deadline task with the provided description and deadline.
     *
     * @param description The received description.
     * @param deadline    The received deadline.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Initializes the Deadline task with the provided description, deadline and completion status.
     *
     * @param description The received description.
     * @param deadline    The received deadline.
     * @param isDone      The received completion status.
     * @param doneAt      The received done at date.
     */
    public Deadline(String description, LocalDate deadline, boolean isDone, LocalDate doneAt) {
        super(description, isDone, doneAt);
        this.deadline = deadline;
    }

    @Override
    public String getTaskTypeIcon() {
        return Deadline.taskType.toString();
    }

    @Override
    public TaskSerializable serialize() {
        return new TaskSerializable(Deadline.taskType, super.description, super.isDone, super.doneAt, this.deadline);
    }

    @Override
    public LocalDate getDate() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), DukeFormatter.formatDate(this.deadline));
    }
}
