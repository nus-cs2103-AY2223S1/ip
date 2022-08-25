package commands;

import drivers.Storage;
import drivers.TaskList;
import drivers.UI;

public class EndChatBotCmd extends Command {
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.goodbye();
    }
}
