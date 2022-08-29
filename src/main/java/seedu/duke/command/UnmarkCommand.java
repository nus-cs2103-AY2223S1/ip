package seedu.duke.command;

import seedu.duke.operations.Storage;
import seedu.duke.operations.TaskList;
import seedu.duke.operations.Ui;

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
