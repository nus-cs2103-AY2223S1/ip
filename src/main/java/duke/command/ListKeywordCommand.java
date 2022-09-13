package duke.command;

import duke.ClientList;
import duke.DukeException;
import duke.task.TaskList;

/**
 * Represents a command that lists out tasks that contains keyword.
 */
public class ListKeywordCommand extends Command {

    private static final ListKeywordCommand LIST_KEYWORD_COMMAND = new ListKeywordCommand();

    /**
     * Returns the list keyword command.
     *
     * @return list keyword command.
     */
    public static ListKeywordCommand of() {
        return LIST_KEYWORD_COMMAND;
    }

    /**
     * Returns String representation of keyword list.
     *
     * @param keywordList list of tasks containing the keyword.
     * @param clientList client list.
     * @return String representation of keyword list.
     * @throws DukeException if keyword list is empty.
     */
    @Override
    public String execute(TaskList keywordList, ClientList clientList) throws DukeException {
        return CommandOutputs.showKeywordList(keywordList);
    }
}
