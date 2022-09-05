package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * A class to handle the marking command.
 */
public class MarkCommand extends Command {
    private int num;

    /**
     * A constructor for mark command.
     *
     * @param num the position of the task to be marked.
     */
    public MarkCommand(int num) {
        this.num = num;
    }

    /**
     * Mark a particular task.
     *
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.getArr().get(this.num - 1).mark();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("How to mark something that is not inside??");
        }
        ui.sayMarked(this.num, tasks.getArr());
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
