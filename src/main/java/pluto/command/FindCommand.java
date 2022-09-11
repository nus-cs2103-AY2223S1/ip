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
        TaskList t = tasks.filter(keyword);
        if (t.nTasks() == 0) {
            return ui.print("No tasks found.");
        } else {
            return ui.print("Here are the matching tasks in your list:\n" + ui.print(t.toString()));
        }

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
