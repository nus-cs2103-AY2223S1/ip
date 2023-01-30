package duke.command;

import duke.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * A class to represent a user command and its functionalities.
 */
public abstract class Command {

    protected Storage storage;
    protected Ui ui;
    protected TaskList taskList;

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

    public abstract String execute() throws DukeException;
}
