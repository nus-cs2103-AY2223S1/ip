package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The HelpCommand class list the available commands.
 */
public class HelpCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printHelpList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
