package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/*
 * Encapsulates a command to find tasks from the list.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a FindCommand.
     *
     * @param keyword Phrase used to search for matching tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand to find tasks from the list.
     *
     * @param tasks TaskList to search from.
     * @param ui Ui that displays success or error to user.
     * @param storage Persistent storage of task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String result = tasks.find(keyword);
        if (result.isEmpty()) {
            return "Sorry, none of your tasks match the search terms.";
        }
        return "Here are the matching tasks in your list:\n" + result;
    }
}
