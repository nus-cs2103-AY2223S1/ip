package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

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
     * @param commandOutputs          user interface of program.
     * @param storage     files storing task list.
     * @return
     * @throws DukeException if keyword list is empty.
     */
    @Override
    public String execute(TaskList keywordList, CommandOutputs commandOutputs, Storage storage) throws DukeException {
        return commandOutputs.showKeywordList(keywordList);
    }
}
