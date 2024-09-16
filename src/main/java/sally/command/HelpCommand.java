package sally.command;

import sally.storage.Storage;
import sally.task.TaskList;
import sally.ui.Ui;

/**
 * HelpCommand class to represent command to display command list.
 *
 * @author liviamil
 */
public class HelpCommand extends Command {
    protected String command;

    public HelpCommand() {}

    public HelpCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (command == null) {
            ui.showHelpList();
        }
        ui.showHelpFor(command);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
