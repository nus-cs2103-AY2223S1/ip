package pluto.command;

import pluto.Storage;
import pluto.TaskList;
import pluto.Ui;

/**
 * Command to return all tasks containing a keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     *
     * Displays all tasks containing a keyword.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredTasks = tasks.filter(keyword);
        return ui.findUi(filteredTasks);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof FindCommand) {
            FindCommand other = (FindCommand) o;
            return this.keyword.equals(other.keyword);
        }
        return false;
    }

}
