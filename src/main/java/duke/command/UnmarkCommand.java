package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * A class to handle the unmarking command.
 */
public class UnmarkCommand extends Command {
    private int num;

    /**
     * A constructor for the unmark command.
     *
     * @param num the position of the task to be unmarked.
     */
    public UnmarkCommand(int num) {
        this.num = num;
    }

    /**
     * Unmark a particular task.
     *
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.getTask(this.num - 1).unmark();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("How to mark something that is not inside??");
        }
        ui.sayUnmarked(this.num, tasks.getTask(this.num - 1));
        storage.overwrite();
    }

    /**
     * Ensures program will not exit.
     *
     * @return a boolean indicating that it should not exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
