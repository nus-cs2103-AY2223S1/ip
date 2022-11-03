package nyanduke.command;

import nyanduke.Storage;
import nyanduke.Ui;
import nyanduke.task.TaskList;

/**
 * The FindCommand class represents a command that
 * finds a task in NyanDuke's task list using a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a new FindCommand.
     *
     * @param keyword The specified keyword to search the task list with.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds tasks in NyanDuke matching the keyword (or phrase) of this instance of FindCommand.
     *
     * @param tasks The specified TaskList involved with the command.
     * @param ui The specified Ui involved with the command.
     * @param storage The specified Storage involved with the command.
     * @return A message of every task found with the matching keyword (or phrase).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingList = tasks.getAllContaining(keyword);
        return ui.showFound(matchingList);
    }
}
