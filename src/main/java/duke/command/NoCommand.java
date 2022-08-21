package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Represents a NoCommand Command
 */
public class NoCommand extends Command {

    /**
     * Command that does nothing
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

    }
}
