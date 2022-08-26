package duke.command;

import duke.errors.DukeException;
import duke.main.Storage;
import duke.main.Ui;
import duke.task.TaskList;

/**
 * Represents a command.
 */
public abstract class Command {

    /**
     * Executes the command
     * @param tasks TaskList object that stores tasks
     * @param ui Ui object deals with user interaction
     * @param storage Storage object that handles text file
     * @throws DukeException exception thrown in TaskList, Ui or Storage methods
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     *
     * @return boolean if the program should exit
     */
    public boolean isExit() {
        return false;
    }
}
