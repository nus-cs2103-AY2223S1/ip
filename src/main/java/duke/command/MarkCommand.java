package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the command to mark a task as completed.
 *
 * @author Tan Jun Wei
 */
public class MarkCommand extends Command {
    /** Index of task to be marked by this command */
    private int index;

    /**
     * Constructs a new MarkCommand with the given index.
     *
     * @param index The index of task to be marked by this command.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task at the given index as completed.
     *
     * @param tasks The task list to be operated on.
     * @param ui The user interface to be used.
     * @param storage The storage to be used.
     * @throws DukeException If any error occurs.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.markItem(index);
        storage.saveAllTasks(tasks);
        ui.showOutput("OK, I've marked the following task:\n  " + task);
    }
}
