package duke.command;

import duke.DukeException;
import duke.StorageInterface;
import duke.TaskList;
import duke.Ui;

/**
 * Abstract superclass that handles Commands given by user.
 */
public abstract class Command {
    protected static Ui ui;
    protected static TaskList taskList;
    protected static StorageInterface storage;
    protected boolean isExit;

    /**
     * Sets Ui object to be used for all Commands;
     * 
     * @param ui Ui object to be used.
     */
    public static void setUi(Ui ui) {
        Command.ui = ui;
    }

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
     * @throws DukeException
     *             Exception might occur when saving tasks to Storage.
     */
    public abstract void execute() throws DukeException;
}
