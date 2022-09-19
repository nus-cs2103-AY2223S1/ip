package duke.command;

import duke.Storage;
import duke.task.TaskList;

/**
 * Class which handles closing the program.
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage) {
        storage.store();
        return "Many thanks from Duke. Have a nice day!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
