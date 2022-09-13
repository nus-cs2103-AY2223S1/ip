package duke.command;

import duke.Responses;
import duke.Storage;
import duke.tasklist.TaskList;

/**
 * Concrete class for the BYE command.
 */
public class ByeCommand extends Command {
    @Override
    public String execute(Storage storage, TaskList taskList) {
        return Responses.BYE_MESSAGE;
    }
}
