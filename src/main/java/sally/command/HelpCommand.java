package sally.command;

import sally.storage.Storage;
import sally.task.TaskList;
import sally.ui.Ui;

public class HelpCommand extends Command {
    protected static final String KEYWORD = "help";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showHelpList();
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
