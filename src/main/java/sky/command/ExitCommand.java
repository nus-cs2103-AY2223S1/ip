package sky.command;

import sky.Storage;
import sky.TaskList;

/**
 * The ExitCommand class deals with exiting the program.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList taskList, Storage storage) {
        String s = "Bye. May all your endeavours fly high!";
        return s;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}