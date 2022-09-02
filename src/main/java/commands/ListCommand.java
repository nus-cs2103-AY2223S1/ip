package commands;

import duke.Storage;
import duke.Ui;
import tasks.TaskList;

/**
 * ListCommand prints out all the tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructor for ListCommand.
     */
    public ListCommand() {
      super();
    }

    @Override
    /**
     * Executes ListCommand by iterating through each task and printing out its string representation.
     *
     * @param taskList Task list to be printed out.
     */
    public String execute(TaskList taskList, Ui ui, Storage s) {
        String str = "";
        if (taskList.getSize() == 0) {
            str = "You have no tasks at the moment!\n";
        }
        for (int i = 1; i <= taskList.getSize(); i++) {
            String addOn = i + ". " + taskList.retrieveTask(i - 1).toString() + "\n";
            str += addOn;
        }
        return str;
    }
}
