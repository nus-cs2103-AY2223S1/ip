package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulates a command.
 */
public abstract class Command {
    /**
     * A function that executes the effect of the command
     *  @param taskList stores the tasks of the program
     * @param storage reads and writes from the text file which stores the tasks in memory
     * @param ui interfaces with the user using the commandline
     * @return
     */
    public abstract String execute(TaskList taskList, Storage storage , Ui ui) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
