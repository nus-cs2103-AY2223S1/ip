package commands;

import duke.Storage;
import duke.Ui;
import tasks.Task;
import tasks.TaskList;

/**
 * FindCommand allows users to search for tasks containing a keyword.
 */
public class FindCommand extends Command {
    String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param keyword Keyword that is to be found in all the tasks returned.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes FindCommand by iterating through the tasklist and returning the string of tasks containing the keyword.
     *
     * @param taskList Task list to find tasks from.
     * @param ui Ui to retrieve return string from.
     * @param s Storage containing the list of tasks or where tasks should be saved.
     * @return String of tasks in the list that contain the keyword.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage s) {
        TaskList found = new TaskList();
        String str = "";
        for (int i = 0; i < taskList.getSize(); i++) {
            Task temp = taskList.retrieveTask(i);
            if (temp.getDescription().contains(keyword)) {
                found.addTask(temp);
            }
        }
        if (found.getSize() == 0) {
            str += "no matching tasks were found :(\n";
        } else {
            for (int i = 1; i <= found.getSize(); i++) {
                String addOn = i + ". " + found.retrieveTask(i - 1).toString() + "\n";
                str += addOn;
            }
        }
        return str;
    }
}
