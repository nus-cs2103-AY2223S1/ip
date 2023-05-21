package duke.undo;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the action of undoing a mark or unmark task command.
 */
public class MarkUndo extends UndoAction {
    private final Task task;
    private final boolean shouldMark;

    /**
     * Constructs a mark / unmark undo action with task and is mark.
     *
     * @param task   Task marked or unmarked.
     * @param isMark True if triggered by mark command.
     */
    public MarkUndo(Task task, boolean isMark) {
        this.task = task;
        shouldMark = !isMark;
    }

    /**
     * {@inheritDoc}
     * Reverses the mark / unmark action.
     */
    @Override
    public void perform(TaskList tasks) {
        if (shouldMark) {
            task.markAsDone();
        } else {
            task.markAsUndone();
        }
    }
}
