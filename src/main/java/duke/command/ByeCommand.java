package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ByeCommand class represents a bye Command given by user.
 */
public class ByeCommand extends Command {

    /**
     * The goodbye message is displayed.
     *
     * @param ui Ui object which handles the interaction with the user
     * @param storage Storage object which handles interaction with data in file
     * @param taskList List of tasks
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.GoodBye();
    }
}
