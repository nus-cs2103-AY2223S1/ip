package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

/**
 * A class that handles delete command.
 */
public class DeleteCommand extends Command {
    private int num;

    /**
     * Constructs the delete command.
     *
     * @param i the position to delete.
     */
    public DeleteCommand(int i) {
        this.num = i;
    }

    /**
     * Deletes existing task.
     *
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage
     * @throws DukeException if command cannot be executed.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            UndoCommand.addHistory(tasks);
            Task deletedTask = tasks.delete(num - 1);
            storage.overwrite();
            ui.sayDeleted(deletedTask, tasks.getSize());
        } catch (IndexOutOfBoundsException e) {
            UndoCommand.deleteLast();
            throw new DukeException("How to delete something that is not inside??");
        }
    }

    /**
     * Ensures that the program does not exit.
     *
     * @return boolean indicating not exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
