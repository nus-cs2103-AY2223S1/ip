package duke.command;

import duke.DukeException;
import duke.util.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents a user command and its functionalities.
 */
public abstract class Command {

    Storage storage;
    Ui ui;
    TaskList taskList;

    /**
     * Constructs a Command object.
     * 
     * @param storage  Storage class to be used
     * @param ui       Ui class to be used
     * @param taskList TaskList to be used
     */
    public Command(Storage storage, Ui ui, TaskList taskList) {
        this.storage = storage;
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Returns true if program is supposed to exit after this command is executed.
     * 
     * @return whether program is supposed to exit after this command is executed
     */
    public boolean isExit() {
        return false;
    }

    public abstract void execute() throws DukeException;
}
