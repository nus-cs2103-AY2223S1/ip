package command;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;

/**
 * Represents virtual concept
 * of an executable command.
 * Provides ability to stop program
 * using isExit() method.
 */
public abstract class Command {
    public abstract void execute(TaskList taskList, UI ui, Storage storage) throws DukeException;


    public abstract boolean isExit();

}
