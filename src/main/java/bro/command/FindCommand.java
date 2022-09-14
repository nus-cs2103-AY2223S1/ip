package bro.command;

import bro.Storage;
import bro.TaskList;
import bro.Ui;

/**
 * FindCommand class.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor of the FindCommand class.
     * @param keyword The word which has to be found from the file.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     *
     * Finds whether any task has the keyword provided.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        TaskList found = tasklist.findTask(keyword);
        return ui.findUi(found);
    }
}
