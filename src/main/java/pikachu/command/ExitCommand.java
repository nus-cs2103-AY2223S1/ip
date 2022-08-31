package pikachu.command;

import pikachu.Storage;
import pikachu.TaskList;
import pikachu.Ui;

public class ExitCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
    }

    public boolean isExit() {
        return true;
    }
}
