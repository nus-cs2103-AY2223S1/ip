package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Unchecks the task to be not completed.
 *
 * @author Bryan Ng Zi Hao
 */
public class UnMarkCommand extends Command {
    private int index;

    /**
     * Constructor for UnMarkCommand.
     *
     * @param index The task of the said index to be unmarked.
     */
    public UnMarkCommand(int index) {
        this.index = index;
    }

    /**
     * Un-marks the task as undone.
     *
     * @param ui The interactions with user being used.
     * @param storage The storage which the data is being stored.
     * @param taskList The list of tasks to be updated in the storage.
     * @throws DukeException There is an error in execution.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task task = taskList.getTask(index);
        task.markAsNotDone();
        System.out.println("\t" + " OK! I've marked this task as not done yet:");
        System.out.println("\t " + task);
    }

    /**
     * Checks if this command will result in the bot to stop running.
     *
     * @return a boolean value.
     */
    @Override
    public boolean isRunning() {
        return true;
    }
}
