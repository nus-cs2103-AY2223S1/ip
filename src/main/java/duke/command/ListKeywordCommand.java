package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command that lists out tasks that contains keyword.
 */
public class ListKeywordCommand extends Command {

    /**
     * Constructor for ListKeywordCommand class.
     */
    public ListKeywordCommand() {
        super(false);
    }

    /**
     * Communicates with user interface to print list of tasks containing the keyword.
     *
     * @param keywordList list of tasks containing the keyword.
     * @param ui user interface of program.
     * @param storage files storing task list.
     * @throws DukeException if keyword list is empty.
     */
    @Override
    public void execute(TaskList keywordList, Ui ui, Storage storage) throws DukeException {
        ui.showKeywordList(keywordList);
    }
}
