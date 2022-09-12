package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to find matching tasks with a keyword.
 *
 * @author dexter-sim
 * @version 0.1
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a find command.
     *
     * @param keyword The keyword to look for matching tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find tasks with the specified keyword.
     * It calls the ui to print the matching task list.
     *
     * @param storage Storage handling the file IO.
     * @param taskList A list of tasks.
     * @param ui A ui to handle printing output.
     * @return A string from the result of execution.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        TaskList matchingTaskList = taskList.findMatchingTasks(keyword);
        return ui.printList(matchingTaskList.toString());
    }
}
