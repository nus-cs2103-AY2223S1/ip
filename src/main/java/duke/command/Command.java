package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The Command class handles the logic between TaskList, Ui and Storage.
 *
 * @author tanjiarong
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
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Returns a boolean indicating if the command is an exit one.
     *
     * @return A boolean indicating if the command is an exit one.
     */
    public abstract boolean isExit();
}
