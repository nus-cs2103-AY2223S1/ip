package sky.command;

import sky.TaskList;
import sky.storage.History;
import sky.storage.Storage;

/**
 * The ExitCommand class deals with exiting the program.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList taskList, Storage storage, History history) {
        String s = "Bye. May all your endeavours fly high!";
        return s;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
