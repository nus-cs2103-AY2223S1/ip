package duke.command;

import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents a exit duke command
 */
public class ExitCommand extends Command {

    /**
     * Executes the command.
     *
     * @param taskList list of stored tasks
     * @return successful exit message
     * @throws DukeException if user input has wrong format
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        return Ui.printExit();
    }
}
