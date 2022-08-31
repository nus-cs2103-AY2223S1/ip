package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to exit Duke.
 *
 * @author Rama Aryasuta Pangestu
 */
public class ExitCommand extends Command {
    /**
     * Constructs a command to exit Duke.
     */
    public ExitCommand() {}

    /**
     * Executes the command by exiting Duke.
     *
     * @param ui       the user interface
     * @param storage  the storage dealing with loading and saving tasks in the save file
     * @param taskList the task list
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.addOutput("Bye. Hope to see you again soon!\n");
    }

    /**
     * Returns true as this is an exit command.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
