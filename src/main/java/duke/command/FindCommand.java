package duke.command;

import duke.ClientList;
import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

/**
 * Represents a command that finds a task by searching for a keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructor for FindCommand class.
     * @param keyword word user wants to find in task list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds all tasks in task list that contains keyword and runs a list keyword command.
     *
     * @param taskList task list.
     * @param storage  files storing task list.
     * @param clientList client list.
     * @return String of tasks that contain the keyword.
     * @throws DukeException if keyword list is empty.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, ClientList clientList) throws DukeException {
        TaskList keywordList = taskList.findKeyWord(keyword);
        return new ListKeywordCommand().execute(keywordList, storage, clientList);
    }
}
