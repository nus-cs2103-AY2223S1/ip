package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to mark a task as done
 *
 * @author Pontakorn Prasertsuk
 */
public class MarkCommand extends Command {

    private int index;

    /**
     * Constructs a new MarkCommand instance
     *
     * @param index the index of the task to be marked
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the MarkCommand
     *
     * @param taskList the task list to be mutated
     * @param ui       the user interface to be used
     * @param storage  the storage to be used
     * @return output to be shown
     * @throws DukeException if an error occurs
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.mark(index, true);
        ui.showOutput("Task " + (index + 1) + " is marked as done!");
        storage.save(taskList.getTaskList());

        return "Task " + (index + 1) + " is marked as done!";
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
