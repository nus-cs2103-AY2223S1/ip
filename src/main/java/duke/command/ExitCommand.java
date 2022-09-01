package duke.command;

import duke.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;


/**
 * Handles the "bye" command.
 */
public class ExitCommand extends Command {

    /**
     * Exits the application.
     * @return String message of running the "bye" command.
     */
    @Override
    public String run(TaskList taskList, Storage storage) {
        return Ui.bye();
    }
}
