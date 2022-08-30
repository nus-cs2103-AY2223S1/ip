package duke.command;

import duke.exception.TaskNotFoundException;
import duke.exception.TaskUnmarkException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a UnmarkCommand object to be called when user inputs 'unmark'.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private static final String TASK_UNMARK = "Aw... Strive to finish it soon! Will mark it as undone: ";
    private int index;

    /**
     * Constructs UnmarkCommand with index to be marked.
     *
     * @param index index to be marked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes UnmarkCommand by marking the task specified by the index as not done.
     *
     * @param tasks list of task where the index specified will be marked as not done.
     * @param ui display the task that was marked not done.
     * @param storage updates the storage when task is marked not done.
     * @throws TaskUnmarkException when task specified by the index is already marked not done.
     * @throws TaskNotFoundException when index given is out of range.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskUnmarkException, TaskNotFoundException {
        Task task = tasks.unmarkTask(index);
        storage.update(tasks, ui);
        displayCommand(ui, TASK_UNMARK, task, tasks.getStatus());
    }

}
