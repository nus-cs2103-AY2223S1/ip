package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * The Command class handles the logic between TaskList, Ui and Storage.
 *
 * @author Tan Jia Rong
 */
public abstract class Command {
    /**
     * Execute the command
     *
     * @param taskList Task list that contains all the tasks.
     * @param ui ui prints output to the user.
     * @param storage Storage that stores the data into user's hard drive.
     * @return Output of the command in the form of a String.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns a boolean indicating the exit status.
     *
     * @return A boolean indicating the exit status.
     */
    public abstract boolean isExit();
}
