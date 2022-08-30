package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.NoMatchingKeywordException;

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
}
