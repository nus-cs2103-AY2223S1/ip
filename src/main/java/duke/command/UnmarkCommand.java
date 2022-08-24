package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class UnmarkCommand extends Command {
    private int num;
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
    @Override
    public boolean isExit() {
        return false;
    }
}
