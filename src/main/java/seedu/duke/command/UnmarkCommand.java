package seedu.duke.command;

import seedu.duke.operations.Storage;
import seedu.duke.operations.TaskList;
import seedu.duke.operations.Ui;

/**
 * Command handles the "unmark" user input. Unmark a Task
 * within the TaskList, indicating that it is not done.
 */
public class UnmarkCommand extends TaskListCommand {
    public UnmarkCommand(String cmd) {
        super(cmd);
    }

    @Override
    void specialisedFunction(TaskList tasks, Ui ui, Storage storage, int taskIndex) {
        boolean isUnchecked = tasks.fetchTask(taskIndex).uncheck();
        if (isUnchecked) {
            ui.showUnmarked();
        } else {
            ui.showAlreadyUnmarked();
        }
        System.out.println(tasks.fetchTask(taskIndex));
    }
}
