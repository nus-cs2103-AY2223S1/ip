package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

/**
 * ListCommand class represents the list command given by the user.
 */
public class ListCommand extends Command {

    /**
     * Prints a list of tasks that are in the task list.
     *
     * @param ui Ui object which handles the interaction with the user.
     * @param storage Storage object which handles interaction with data in file.
     * @param taskList List of tasks.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.printBorder();
        if (taskList.getSize() == 0) {
            String message = "\t\t\t" + "No items are in the list";
            ui.displayCommandMessage(message, null, null);
        }
        for (int i = 0; i < taskList.getSize(); i++) {
            Task currTask = taskList.getTask(i);
            String itemDisplayed = String.format("\t\t\t%d. %s", i + 1, currTask);
            ui.displayCommandMessage(itemDisplayed, null, null);
        }
        ui.printBorder();
    }
}
