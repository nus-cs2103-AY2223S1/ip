package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Find command for Duke application.
 *
 * @author Farrel Dwireswara Salim
 */
public class FindCommand implements Command {
    private final String[] keyWords;

    /**
     * Constructs a new FindCommand instance based on keyword.
     *
     * @param keyWord the keyword string.
     * @throws DukeException If the keyword string is empty.
     */
    public FindCommand(String keyWord) throws DukeException {
        if (keyWord == null || keyWord == "") {
            throw new DukeException("The keyword after find command "
                    + "must be a non-empty string!");
        }


        this.keyWords = keyWord.split(",");
    }

    /**
     * Executes the FindCommand.
     *
     * @param ui the Ui object to handle user interface.
     * @param storage the storage to be used by the FindCommand.
     * @param taskList the task list to be used by the FindCommand.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        TaskList filteredTasks = taskList.filterByKeyWord(this.keyWords);
        ui.printMatchedTaskList(filteredTasks);
    }
}
