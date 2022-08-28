package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to mark a task as not done
 *
 * @author Pontakorn Prasertsuk
 */
public class UnmarkCommand extends Command {

    private int index;

    /**
     * Constructs a new UnmarkCommand instance
     *
     * @param index the index of the task to be unmarked
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the UnmarkCommand
     *
     * @param taskList the task list to be mutated
     * @param ui       the user interface to be used
     * @param storage  the storage to be used
     * @return output to be shown
     * @throws DukeException if an error occurs
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.mark(index, false);
        ui.showOutput("Task " + (index + 1) + " is marked as not done!");
        storage.save(taskList.getTaskList());

        return "Task " + (index + 1) + " is marked as not done!";
    }

    /**
     * Returns false as this is not the exit command
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
