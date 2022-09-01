package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

/**
 * PrintListCommand implements method for printing out the task list.
 *
 * @author Isaac Li Haoyang
 * @version v0.1
 */
public class PrintListCommand extends Command {

    /**
     * Prints out the current task list.
     *
     * @param taskList the task list to be printed
     * @param ui the ui to display message after the task list is printed
     * @param storage placeholder to match parameters defined in parent abstract class Command
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        taskList.printList();
        ui.printListMessage(taskList);
    }

    /**
     * Prevents the program from terminating in Duke.run().
     *
     * @return False as this is not the 'exit' command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
