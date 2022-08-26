package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Represents all commands entered.
 */
public class Command {

    /**
     * Executes the task when corresponding command is called.
     *
     * @param tasks list of tasks
     * @param ui interaction with the user
     * @param storage storage of all the tasks
     * @throws DukeException  If an error occurs when trying to execute the task.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    }

    /**
     * Keeps the bot running except when exit command is called
     *
     * @return false when exit command is not called.
     */
    public boolean exitBot() {
        return false;
    }
}
