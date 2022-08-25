package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
     * @param ui user interface of program.
     * @param storage files storing task list.
     * @throws DukeException if keyword list is empty.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList keywordList = taskList.findKeyWord(keyword);
        new ListKeywordCommand().execute(keywordList, ui, storage);
    }
}
