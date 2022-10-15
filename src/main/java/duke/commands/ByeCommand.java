package duke.commands;

import duke.TaskList;
import duke.Storage;

/**
 * A <code>Command</code> class that handles the exiting of the application
 */
public class ByeCommand extends Command {

    /**
     * @inheritDoc
     */
    @Override
    public String execute(Storage storage, TaskList tl) {
        tl.closeTaskList();
        String response = "Goodbye! Looking forward to see you again!";
        return response;
    }
}
