package duke.command;

import duke.CommandHistory;
import duke.MultiLineFormatter;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

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
     * @param commandHistory History of commands made.
     * @return String representing list of tasks.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList,
            CommandHistory commandHistory) {
        MultiLineFormatter mFormatter = new MultiLineFormatter();
        if (taskList.getSize() == 0) {
            String message = "No items are in the list";
            mFormatter.add(message);
        }
        for (int i = 0; i < taskList.getSize(); i++) {
            Task currTask = taskList.getTask(i);
            String itemDisplayed = String.format("%d. %s\n", i + 1, currTask);
            mFormatter.add(itemDisplayed);
        }
        return mFormatter.getFullMessage();
    }

    /**
     * Returns message that this command cannot be undone.
     *
     * @param ui Ui object which handles the interaction with the user.
     * @param storage Storage object which handles interaction with data in file.
     * @param taskList List of tasks.
     * @param commandHistory History of commands made.
     * @return Message that this command cannot be undone.
     */
    @Override
    public String undoExecute(Ui ui, Storage storage, TaskList taskList,
            CommandHistory commandHistory) {
        return "There are no more changes to be undone!";
    }
}
