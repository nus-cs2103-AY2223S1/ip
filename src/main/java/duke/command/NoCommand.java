package duke.command;

import duke.Storage;
import duke.task.TaskList;

/**
 * Represents a NoCommand Command
 */
public class NoCommand extends Command {

    /**
     * Command that does nothing
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {

    }
}
