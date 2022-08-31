package duke.command;

import duke.manager.Storage;
import duke.manager.TaskList;

/**
 * Represents a command to exit from the task manager program.
 */
public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        String response = "Bye. Hope to see you again soon!\nBut close the GUI yourself!";

        return response;
    }
}
