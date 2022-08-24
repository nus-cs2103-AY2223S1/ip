package duke.models.task;

import java.time.LocalDate;

import duke.models.serializable.TaskSerializable;
import duke.utils.DukeFormatter;

/**
 * Encapsulates a task that needs to be done before a specific date/time,
 * e.g., submit report by 11/10/2019 5pm
 *
 * @author Emily Ong Hui Qi
 */

public class Deadline extends Task {
    private static final TaskType taskType = TaskType.DEADLINE;
    protected LocalDate deadline;

    /**
     * TODO: Add JavaDocs
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * TODO: Add JavaDocs
     */
    public Deadline(String description, LocalDate deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * TODO: Add JavaDocs
     */
    @Override
    public String getTaskTypeIcon() {
        return Deadline.taskType.toString();
    }

    /**
     * TODO: Add JavaDocs
     */
    @Override
    public TaskSerializable serialize() {
        return new TaskSerializable(Deadline.taskType, super.description, super.isDone, this.deadline);
    }

    /**
     * TODO: Add JavaDocs
     */
    @Override
    public LocalDate getDate() {
        return this.deadline;
    }

    /**
     * TODO: Add JavaDocs
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), DukeFormatter.formatDate(this.deadline));
    }
}
