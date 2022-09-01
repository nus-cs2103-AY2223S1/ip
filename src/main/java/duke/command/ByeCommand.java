package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * The ByeCommand class represents the command of exiting the application.
 */
public class ByeCommand extends Command {
    private static final String GOODBYE_MSG = "Bye. Hope to see you again soon!";

    /**
     * Initializes an instance of ByeCommand.
     */
    public ByeCommand() {
        super(true);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        return GOODBYE_MSG;
    }
}
