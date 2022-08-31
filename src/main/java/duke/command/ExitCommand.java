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
     */
    @Override
    public void run(TaskList taskList, Storage storage) {
        Ui.bye();
        System.exit(0);
    }
}
