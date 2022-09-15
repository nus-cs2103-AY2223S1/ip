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

    /**
     * Executes ListCommand by iterating through each task and printing out its string representation.
     *
     * @param taskList Task list to be printed out.
     * @param ui Ui to retrieve return string from.
     * @param s Storage containing the list of tasks or where tasks should be saved.
     * @return String of listed out tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage s) {
        String str = "";
        if (taskList.getSize() == 0) {
            str = "you have 0 tasks right now!\n";
        }
        for (int i = 1; i <= taskList.getSize(); i++) {
            String addOn = i + ". " + taskList.retrieveTask(i - 1).toString() + "\n";
            str += addOn;
        }
        return str;
    }
}
