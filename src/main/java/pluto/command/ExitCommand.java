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

    @Override
    public boolean equals(Object o) {
        if (o instanceof ExitCommand) {
            return true;
        }
        return false;
    }
}
