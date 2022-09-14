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
     * Constructs the mark command.
     *
     * @param num the position of the task to be marked.
     */
    public MarkCommand(int num) {
        this.num = num;
    }

    /**
     * Marks a particular task.
     *
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage
     * @throws DukeException if command cannot be executed.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.getArr().get(this.num - 1).mark();
            ui.sayMarked(this.num, tasks.getArr());
            storage.overwrite();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("How to mark something that is not inside??");
        }
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
