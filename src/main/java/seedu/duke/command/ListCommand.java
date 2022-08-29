package seedu.duke.command;

import seedu.duke.operations.Storage;
import seedu.duke.operations.TaskList;
import seedu.duke.operations.Ui;

/**
 * Command handles the "list" user input.
 */
public class ListCommand extends Command {

    /**
     * Prints all the Task within TaskList. If there are none
     * print the message indicating so.
     *
     * @param tasks             TaskList of Duke
     * @param ui                Ui of Duke
     * @param storage           Storage of Duke
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.showNoTask();
        }
        for (int i = 1; i <= tasks.numOfTasks(); i++) {
            System.out.println(String.format("%d. %s", i, tasks.fetchTask(i).toString()));
        }
    }
}
