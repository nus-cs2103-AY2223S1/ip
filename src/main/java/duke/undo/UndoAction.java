package duke.undo;

import duke.task.TaskList;

/**
 * Represents the action of undoing a command.
 */
public abstract class UndoAction {
    /**
     * Undoes the effect of a command.
     *
     * @param tasks Tasks to be performed on.
     */
    public abstract void perform(TaskList tasks);
}
