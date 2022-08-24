package duke.commands;

import duke.ui.Ui;
import duke.data.TaskList;
import duke.storage.Storage;

/**
 * This class encapsulates an Exit Command
 */
public class ListCommand extends Command {

    /**
     * Checks if the command is an Exit Command
     * @return True if it is an Exit Command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the Command
     * @param ui Prints the messages based on the type of Command
     * @param storage Saves the modified list of tasks
     * @param taskList List of tasks
     * @throws DukeException if invalid inputs are provided
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        String list = taskList.list();
        ui.printList(list);
    }
}
