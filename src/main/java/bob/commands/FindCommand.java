package bob.commands;

import bob.Storage;
import bob.TaskList;
import bob.Ui;

/**
 * FindCommand class to handle "find" keyword
 */
public class FindCommand extends Command {

    private String searchWord;

    /**
     * Constructor for FindCommand
     *
     * @param searchWord word to search for
     */
    public FindCommand(String searchWord) {
        super();
        this.searchWord = searchWord;
    }

    @Override
    public String executeCommand(TaskList taskList, Storage storage, Ui ui) {
        String response = "here are all tasks with" + " '" + this.searchWord + "'";
        storage.save(taskList);
        return ui.displayTaskList(taskList.findTask(this.searchWord), response);
    }
}
