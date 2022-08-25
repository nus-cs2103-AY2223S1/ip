package duke.command;

import duke.TaskList;
import duke.storage.Storage;

/**
 * When the command is not recognised
 */
public class InvalidCommand extends Command {
    public InvalidCommand() {

    }
    @Override
    public void execute(TaskList tasks, Storage storage) {

    }
}
