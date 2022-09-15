package duke.command;

import duke.DukeException;
import duke.StorageInterface;
import duke.TaskList;

/**
 * Abstract superclass that handles Commands given by user.
 */
public abstract class Command {
    protected static TaskList taskList;
    protected static StorageInterface storage;
    protected boolean isExit;

    /**
     * Sets TaskList object to be used for all Commands;
     *
     * @param taskList TaskList object to be used.
     */
    public static void setTaskList(TaskList taskList) {
        Command.taskList = taskList;
    }

    /**
     * Sets Storage object to be used for all Commands;
     *
     * @param storage Storage object to be used.
     */
    public static void setStorage(StorageInterface storage) {
        Command.storage = storage;
    }

    /**
     * Returns if application should exit.
     *
     * @return isExit variable.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes Command.
     *
     * @return Return message of command.
     * @throws DukeException Exception might occur when saving tasks to Storage.
     */
    public abstract String execute() throws DukeException;
}
