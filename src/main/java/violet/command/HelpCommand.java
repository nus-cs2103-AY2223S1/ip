package violet.command;

import violet.TaskList;
import violet.Ui;

/**
 * HelpCommand class executes the help command.
 */
public class HelpCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui) {
        this.response = ui.showHelp();
    }

}
