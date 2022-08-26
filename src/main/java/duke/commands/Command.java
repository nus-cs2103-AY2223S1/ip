package duke.commands;

import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

/**
 * This interface encapsulates the information to be performed based on each command.
 */
public interface Command {
    /**
     * Executes command.
     * Displays a message, modifies TaskList, find or store tasks based on command executed.
     *
     * @param tasks TaskList to store tasks.
     * @param ui User interface that handles interaction with user.
     * @param storage Storage to deal with loading tasks from the file and saving tasks in the file.
     */
    void execute(TaskList tasks, Ui ui, Storage storage);
}
