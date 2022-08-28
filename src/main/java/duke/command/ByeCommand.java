package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Bye command for Duke application.
 *
 * @author Farrel Dwireswara Salim
 */
public class ByeCommand implements Command {
    /**
     * Constructs a new instance of ByeCommand.
     */
    public ByeCommand() {}

    /**
     * Executes the ByeCommand
     *
     * @param ui the Ui object to handle user interface.
     * @param storage the storage used by the ByeCommand.
     * @param taskList the task list used by the ByeCommand.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.printFarewellMessage();
    }
}
