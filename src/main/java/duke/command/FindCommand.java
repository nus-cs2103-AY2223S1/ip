package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.NoMatchingKeywordException;

/**
 * Representation of a command to find a task in taskList.
 */
public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /*
     * Attempts to find task specified from the taskList.
     * If successful, Ui shows success on CLI.
     * @throws DukeException when task specified is in improper format
     *         or task cannot be found.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) throws NoMatchingKeywordException {
        TaskList success = taskList.findTask(this.keyword);
        return ui.showFind(success);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FindCommand) {
            FindCommand x = (FindCommand) obj;
            if (this.keyword == null || x.keyword == null) {
                return false;
            }
            return this.keyword.equals(x.keyword);
        }
        return false;
    }
}
