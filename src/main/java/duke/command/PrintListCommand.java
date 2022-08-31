package duke.command;

import duke.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

/**
 * Handles the "list" command.
 * @author Jason
 */
public class PrintListCommand extends Command {

    /**
     * Prints all values of list currently.
     * @param taskList TaskList to print from.
     * @param storage Storage to store duke.TaskList data to.
     */
    @Override
    public void run(TaskList taskList, Storage storage) {
        StringBuilder message = new StringBuilder();
        // Empty list
        if (taskList.size() == 0) {
            message = new StringBuilder("List is currently empty!\n");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                message.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
            }
        }
        Ui.printMessage(String.valueOf(message));
    }
}
