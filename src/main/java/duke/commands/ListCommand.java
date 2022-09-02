package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Command to list out all current tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the command.
     * @param taskList
     * @param ui User Interface of the to-do-list.
     * @param storage Storage option.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {

        StringBuilder sb = new StringBuilder();

        System.out.println("Here are the tasks in your list:");
        sb.append("Here are the tasks in your list:\n");

        if (taskList.size() == 0) {
            System.out.println("Oops, there are no tasks. Please add tasks to the list!");
            sb.append("Oops, there are no tasks. Please add tasks to the list!");
        } else {
            for (int i = 0; i < taskList.size(); ++i) {
                System.out.printf(" %d. %s\n", i + 1, taskList.get(i));
                sb.append(String.format(" %d. %s\n", i + 1, taskList.get(i)));
            }
        }
        return sb.toString();
    }


    @Override
    public boolean isEnd() {
        return false;
    }
}
