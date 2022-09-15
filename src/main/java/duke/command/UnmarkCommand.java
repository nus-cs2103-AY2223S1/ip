package duke.command;

import duke.DukeException;
import duke.Message;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Unmarks a task in the existing storage
 */
public class UnmarkCommand extends Command {

    private final int index;

    /**
     * Creates a command to unmark a task in the existing storage
     *
     * @param index the index of the task to be unmarked
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Determines if the command should end the program for the user
     *
     * @return false by default
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the unmarking of a task in the local storage
     *
     * @param tasks the list of tasks to be modified in execution
     * @param ui the ui used to display messages to the user once the task is successfully unmarked
     * @param storage the storage to be modified in execution
     * @throws DukeException if the change cannot be saved in storage successfully or task cannot be unmarked
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.unmarkTaskAtPos(this.index);
            Task currentTask = tasks.getTask(this.index);
            storage.save(tasks);
            return ui.showUnmarked(currentTask);
        } catch (IndexOutOfBoundsException e) {
            if (tasks.getCount() == 0) {
                throw new DukeException(Message.INVALID_ACCESS_EMPTY_TASKLIST);
            } else {
                throw new DukeException(Message.returnTaskNotFound(tasks));
            }
        }
    }

}



