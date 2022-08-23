package jean.command;

import jean.storage.Storage;
import jean.task.TaskList;
import jean.ui.Ui;

import java.io.IOException;

/**
 * An abstract class which encapsulates a Command object.
 */
public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws IOException;

    /**
     * Checks if the Command object is an instance of ExitCommand.
     *
     * @return Returns true if it is an ExitCommand. Returns false otherwise.
     */
    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
