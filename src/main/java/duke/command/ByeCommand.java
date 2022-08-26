package duke.command;

import duke.main.Ui;
import duke.main.TaskList;
import duke.main.Storage;

/**
 * Represents a command that is used to print out a bye message.
 */
public class ByeCommand extends Command {

    /**
     * Execute method that is used to print out a bye message through ui.
     *
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.bye();
    }
}
