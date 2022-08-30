package duke.commands;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.Ui;
import duke.tasks.TaskList;

/**
 * Parent class of all commands with single execute method
 */

public abstract class Command {

    private boolean isExit = false;

    /**
     * Executes a command that may or may not modify the TaskList
     *
     * @param tasks   TaskList containing all tasks so far
     * @param ui      Ui which prints out messages to users
     * @param storage Storage with dataFile of tasks
     * @return String to print out to user
     * @throws DukeException If command is not executable due to errors in user input
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return this.isExit;
    }

    public void makeTrueExit() {
        this.isExit = true;
    }
}
