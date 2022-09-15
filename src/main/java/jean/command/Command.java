package jean.command;

import java.io.IOException;

import jean.storage.Storage;
import jean.task.TaskList;
import jean.ui.Ui;

/**
 * An abstract class which encapsulates a Command object.
 */
public abstract class Command {
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws IOException;

    /**
     * Checks if the Command object is an instance of ExitCommand.
     *
     * @return Returns true if it is an ExitCommand. Returns false otherwise.
     */
    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
