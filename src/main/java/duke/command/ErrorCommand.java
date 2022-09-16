package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;


/**
 * Represents a command that is used to inform the user of a wrong input.
 */
public class ErrorCommand extends Command {

    /**
     * Returns a string after method is used to print out an error message
     * to inform the User that they
     * have inputted wrongly.
     *
     * @param taskList
     * @param archiveTaskList
     * @param storage
     * @param archiveStorage
     * @param ui
     */
    @Override
    public String execute(TaskList taskList, TaskList archiveTaskList, Storage storage,
                          Storage archiveStorage, Ui ui) {
        return "Sorry, I do not accept that as a task!";
    }
}
