package amanda.command;

import amanda.manager.StoreManager;
import amanda.manager.TaskList;
import amanda.ui.Ui;

/**
 * ListCommand is a class that lists the current task in the task list.
 */
public class ListCommand extends Command {

    /**
     * Iterate through the current task list and prints the tasks.
     * @param tasks the current task list.
     * @param store the store manager that write any changes to the storage file.
     */
    @Override
    public void execute(TaskList tasks, StoreManager store) {
        Ui.listResponse();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < TaskList.getList().size(); i++) { // iterate through current task list and print each task.
            Ui.addResponse((i + 1) + "." + TaskList.getList().get(i) + "\n");
            res.append(i + 1).append(".").append(TaskList.getList().get(i)).append("\n");
        }
        Ui.addResponse("");
        System.out.println(res);
    }
}
