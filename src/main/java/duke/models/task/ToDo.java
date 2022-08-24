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
     * @param description The received description
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Initializes the ToDo task with the provided description and completion status.
     *
     * @param description The received description
     * @param isDone The received completion status
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public TaskSerializable serialize() {
        return new TaskSerializable(ToDo.taskType, super.description, super.isDone, null);
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
