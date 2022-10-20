package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A command class that encapsulates the action of exiting Duke.
 */
public class ExitCommand extends Command {

    public static final String COMMAND = "BYE";

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showBye();
    }

}
