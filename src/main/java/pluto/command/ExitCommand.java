package pluto.command;

import pluto.Storage;
import pluto.TaskList;
import pluto.Ui;

public class ExitCommand extends Command {
    public boolean isExit() {
        return true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print("\tSee You Later!");
    }
}
