package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

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
        super(false);
        this.keyword = keyword;
    }

    /**
     * Finds all tasks in task list that contains keyword and runs a list keyword command.
     *
     * @param taskList task list.
     * @param commandOutputs       user interface of program.
     * @param storage  files storing task list.
     * @return
     * @throws DukeException if keyword list is empty.
     */
    @Override
    public String execute(TaskList taskList, CommandOutputs commandOutputs, Storage storage) throws DukeException {
        TaskList keywordList = taskList.findKeyWord(keyword);
        return new ListKeywordCommand().execute(keywordList, commandOutputs, storage);
    }
}
