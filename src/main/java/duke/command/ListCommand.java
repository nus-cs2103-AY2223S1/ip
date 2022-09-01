package duke.command;

import duke.MultiLineFormatter;
import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

/**
 * ListCommand class represents the list command given by the user.
 */
public class ListCommand extends Command {

    /**
     * Returns the string representation of
     * a list of tasks that are in the task list.
     *
     * @param ui Ui object which handles the interaction with the user.
     * @param storage Storage object which handles interaction with data in file.
     * @param taskList List of tasks.
     * @return String representing list of tasks.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) {
        MultiLineFormatter mf = new MultiLineFormatter();
        if (taskList.getSize() == 0) {
            String message = "\t\t\t" + "No items are in the list";
            mf.add(message);
        }
        for (int i = 0; i < taskList.getSize(); i++) {
            Task currTask = taskList.getTask(i);
            String itemDisplayed = String.format("%d. %s\n", i + 1, currTask);
            mf.add(itemDisplayed);
        }
        return mf.getFullMessage();
    }
}
