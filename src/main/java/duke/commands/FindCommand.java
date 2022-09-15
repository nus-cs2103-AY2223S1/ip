package duke.commands;

import java.util.List;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Command that represents the find command.
 */
public class FindCommand implements Command {
    private String searchKeyword;

    /**
     * Default constructor for the find command.
     * @param searchKeyword keyword to be searched in task names.
     */
    public FindCommand(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    /**
     * Runs the find command by printing a list of tasks that match the search keyword.
     *
     * @param tasks TaskList that contains the temporary tasks.
     * @param storage Storage that the tasks are saved at.
     * @return String output of executing the task.
     * @throws DukeException If any error occurs.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        List taskListWithKeyword = tasks.search(searchKeyword);
        if (taskListWithKeyword.size() == 0) {
            return "No matching tasks found!";
        }
        String output = "";
        output += "Here are the matching tasks in your list:\n";
        for (int i = 0; i < taskListWithKeyword.size(); i++) {
            int printedIndex = i + 1;
            output += printedIndex + ": " + taskListWithKeyword.get(i) + "\n";
        }
        return output;
    }
    @Override
    public boolean isBye() {
        return false;
    }
}
