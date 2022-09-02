package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Class representing an executable Command.
 */
public abstract class Command {

    /**
     * Executes the command.
     * @param tasks Current list of to-do-list tasks.
     * @param ui User Interface of the to-do-list.
     * @param storage Storage option.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns a flag whether the command will terminate the program.
     */
    public abstract boolean isEnd();

}
