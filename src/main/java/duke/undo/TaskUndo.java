package duke.undo;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the action of undoing a add task command.
 */
public class TaskUndo extends UndoAction {
    private final Task task;

    /**
     * Constructs a task undo action with task.
     *
     * @param task Task added.
     */
    public TaskUndo(Task task) {
        this.task = task;
    }

    /**
     * {@inheritDoc}
     * Removes the added task.
     */
    @Override
    public void perform(TaskList tasks) {
        tasks.removeTask(task);
    }
}
