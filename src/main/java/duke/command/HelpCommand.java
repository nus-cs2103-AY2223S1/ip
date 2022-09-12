package duke.command;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Help command
 */
public class HelpCommand extends Command {

    public HelpCommand() {
        super();
    }


    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return ui.showHelpMessage();
    }
}
