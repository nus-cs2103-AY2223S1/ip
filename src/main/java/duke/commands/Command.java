package duke.commands;

import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

/**
 * This class encapsulates a set of instructions to be performed by Duke.
 */
public interface Command {

    /**
     * Executes the command from the user.
     *
     * @param tasks The list of tasks stored by the user.
     * @param ui The user interface.
     * @param storage The storage.
     */
    void execute(TaskList tasks, Ui ui, Storage storage);
}
