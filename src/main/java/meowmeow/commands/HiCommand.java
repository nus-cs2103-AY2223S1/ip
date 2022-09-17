package meowmeow.commands;

import meowmeow.Storage;
import meowmeow.TaskList;
import meowmeow.Ui;

public class HiCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showWelcome();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
