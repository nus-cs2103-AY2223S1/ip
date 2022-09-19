package justin.command;

import justin.Storage;
import justin.TaskList;
import justin.Ui;

public class HelpCommand extends Command {
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        return ui.getHelpMessage();
    }
}
