package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;


/**
 * Represents a command that is used to inform the user of a wrong input.
 */
public class ErrorCommand extends Command {

    /**
     * Execute method that is used print out an error message to inform the User that they
     * have inputted wrongly.
     *
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.repeater("Sorry, I do not accept that as a task!");
    }
}
