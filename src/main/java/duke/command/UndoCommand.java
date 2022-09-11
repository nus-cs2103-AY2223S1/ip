package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to undo a command.
 *
 * @author Rama Aryasuta Pangestu
 */
public class UndoCommand extends Command {
    /**
     * Constructs a command to undo the last command which modifies the task list, which are
     * the Add, Delete, Mark, and UnMark Command.
     */
    public UndoCommand() {}

    /**
     * Executes the command by undoing the last command which modifies the task list.
     *
     * @param ui       the user interface
     * @param storage  the storage dealing with loading and saving tasks in the save file
     * @param taskList the task list
     * @throws DukeException if an error occurs when saving the task list to the save file
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        taskList.undo();
        ui.addOutput("OK, I've undo-ed the last command.\n");
        if (taskList.size() > 0) {
            ui.addOutput("Now here are the tasks in your list:\n");
            ui.addOutput(taskList.toString());
        } else {
            ui.addOutput("Now your list is empty :(\n");
        }
        storage.save(taskList);
    }

    /**
     * Returns false as this is not an exit command.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
