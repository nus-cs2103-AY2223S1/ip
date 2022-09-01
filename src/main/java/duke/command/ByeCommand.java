package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ByeCommand is the command when the user inputs bye.
 */
public class ByeCommand extends Command {

    /**
     * Executes the specific command corresponding to the type of input the user gives.
     *
     * @param list List of tasks.
     * @param ui Ui to print messages.
     * @param storage To save the list after making changes.
     * @return String that matches the command input.
     */
    @Override
    public String execCommand(TaskList list, Ui ui, Storage storage) {
        return ui.showBye();
    }
}
