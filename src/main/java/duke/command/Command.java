package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * An abstract class which represents the Command given by the user.
 */
public abstract class Command {

    public abstract String execute(Ui ui, Storage storage, TaskList taskList);

    /**
     * Checks whether the current command is a ByeCommand.
     *
     * @return true if command is a ByeCommand; else return false.
     */
    public Boolean isTerminated() {
        return this instanceof ByeCommand;
    }
}
