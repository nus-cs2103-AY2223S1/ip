package duke.command;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Command to exit the program and show bye message
 */
public class ByeCommand extends Command {
    public ByeCommand() {
        super();
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return ui.showByeMessage();
    }

}
