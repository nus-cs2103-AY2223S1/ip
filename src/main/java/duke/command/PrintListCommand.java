package duke.command;

import duke.utils.Storage;
import duke.TaskList;
import duke.utils.Ui;

public class PrintListCommand extends Command {

    /**
     * Prints all values of list currently
     * @param taskList duke.TaskList to print from
     * @param storage duke.utils.Storage to store duke.TaskList data to
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
