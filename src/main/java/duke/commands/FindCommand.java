package duke.commands;

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
     * @param tasks TaskList that contains the temporary tasks.
     * @param storage Storage that the tasks are saved at.
     * @throws DukeException If any error occurs.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        TaskList taskListWithKeyword = tasks.search(searchKeyword);
        if (taskListWithKeyword.getSize() > 0) {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < taskListWithKeyword.getSize(); i++) {
                int printedIndex = i + 1;
                System.out.println(printedIndex + ": " + taskListWithKeyword.get(i));
            }
        } else {
            System.out.println("No matching tasks found!");
        }
    }
}
