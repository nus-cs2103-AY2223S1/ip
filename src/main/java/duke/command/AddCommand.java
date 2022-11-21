package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 *  An abstract class for Add commands.
 */
public abstract class AddCommand extends Command {

    /**
     * Adds new task.
     *
     * @param tasks the list of tasks.
     * @param ui the user interface.
     * @param storage the storage.
     * @throws DukeException if command cannot be executed.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        UndoCommand.addHistory(tasks);
        this.add(tasks); //add into tasks
        ui.sayAdded(tasks.getArr());
        storage.overwrite(); //overwrite
    }

    /**
     * Adds tasks to the list.
     *
     * @param tasks
     * @throws DukeException if cannot be added.
     */
    public abstract void add(TaskList tasks) throws DukeException;

    /**
     * Makes sure program continues and not exit.
     * @return boolean indicating not exit.
     */
    @Override
    public boolean isExit() {
        return false;
    };
}
