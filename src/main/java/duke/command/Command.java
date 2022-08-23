package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command for Duke application.
 *
 * @author Farrel Dwireswara Salim
 */
public interface Command {
    /**
     * Executes the command.
     *
     * @param ui the Ui object to handle user interface.
     * @param storage the storage used by the Command.
     * @param taskList the task list used by the Command.
     * @throws DukeException If Duke fails to execute the command.
     */
    void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException;
}
