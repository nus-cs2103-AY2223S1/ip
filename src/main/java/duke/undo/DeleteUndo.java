package duke.undo;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the action of undoing a delete task command.
 */
public class DeleteUndo extends UndoAction {
    private final Task task;

    /**
     * Constructor for a delete undo action.
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
