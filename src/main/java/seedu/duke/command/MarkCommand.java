package seedu.duke.command;

import seedu.duke.operations.Storage;
import seedu.duke.operations.TaskList;
import seedu.duke.operations.Ui;

public class MarkCommand extends TaskListCommand {

    public MarkCommand(String cmd) {
        super(cmd);
    }

    void specialisedFunction(TaskList tasks, Ui ui, Storage storage, int taskIndex){
        boolean isChecked = tasks.fetchTask(taskIndex).check();
        if (isChecked) {
            ui.showMarked();
        } else {
            ui.showAlreadyMarked();
        }
        System.out.println(tasks.fetchTask(taskIndex));
    }
}
