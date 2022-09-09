package duke.command;

import duke.ClientList;
import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

/**
 * Represents a command that lists out tasks that contains keyword.
 */
public class ListKeywordCommand extends Command {

    /**
     * Communicates with user interface to print list of tasks containing the keyword.
     *
     * @param keywordList list of tasks containing the keyword.
     * @param storage files storing task list.
     * @param clientList client list.
     * @return String representation of keyword list.
     * @throws DukeException if keyword list is empty.
     */
    @Override
    public String execute(TaskList keywordList, Storage storage, ClientList clientList) throws DukeException {
        return CommandOutputs.showKeywordList(keywordList);
    }
}
