package commands;

import data.TaskList;
import storage.Storage;

/**
 * Command to exit Duke.
 */
public class ByeCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
