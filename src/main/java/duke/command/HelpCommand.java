package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A command class that encapsulates the action of calling help menu in Duke.
 */
public class HelpCommand extends Command {

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showHelpMenu();
    }


}
