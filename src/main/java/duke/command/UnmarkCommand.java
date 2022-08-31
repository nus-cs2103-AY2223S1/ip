package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeOutOfBoundException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The UnmarkCommand class deletes a task from TaskList.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructor for a unmarkCommand.
     *
     * @param index index of Task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task unmarkedTask = taskList.unmark(index);
            String message = ui.printUnmarkTask(unmarkedTask) + '\n';
            storage.save(taskList);
            return message;
        } catch (DukeOutOfBoundException e) {
            return ui.printErr(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
