package duke.models.task;

import java.time.LocalDate;

import duke.models.serializable.TaskSerializable;

/**
 * Encapsulates a {@link Task} without any date attached to it.
 *
 * @author Emily Ong Hui Qi
 */

public class ToDo extends Task {
    private static final TaskType taskType = TaskType.TODO;

    /**
     * Initializes the ToDo task with the provided description.
     *
     * @param description The received description.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Initializes the ToDo task with the provided description and completion status.
     *
     * @param description The received description.
     * @param isDone      The received completion status.
     * @param doneAt      The received done at date.
     */
    public ToDo(String description, boolean isDone, LocalDate doneAt) {
        super(description, isDone, doneAt);
    }

    @Override
    public TaskSerializable serialize() {
        return new TaskSerializable(ToDo.taskType, super.description, super.isDone, super.doneAt, null);
    }

    @Override
    public String getTaskTypeIcon() {
        return ToDo.taskType.toString();
    }

    @Override
    public LocalDate getDate() {
        return null;
    }
}
