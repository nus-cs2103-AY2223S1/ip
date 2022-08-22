package sky.command;

import sky.Storage;
import sky.TaskList;
import sky.Ui;

/**
 * The ExitCommand class deals with exiting the program.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String s = "  Bye. May all your endeavours fly high!";
        ui.endConvo();
        return s;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}