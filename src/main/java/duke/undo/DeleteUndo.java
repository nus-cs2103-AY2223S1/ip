package duke.undo;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the action of undoing a delete task command.
 */
public class DeleteUndo extends UndoAction {
    private final Task task;

    /**
     * Constructs a delete undo action with a task.
     *
     * @param task Task deleted.
     */
    public DeleteUndo(Task task) {
        this.task = task;
    }

    /**
     * {@inheritDoc}
     * Adds the task back.
     */
    @Override
    public void perform(TaskList tasks) {
        tasks.addTask(task);
    }
}
