package seedu.duke.command;

import seedu.duke.operations.Storage;
import seedu.duke.operations.TaskList;
import seedu.duke.operations.Ui;

/**
 * Command handles "mark" user input. Marks a Task as done.
 */
public class MarkCommand extends TaskListCommand {

    /**
     * Constructor for the Command.
     *
     * @param cmd   User input
     */
    public MarkCommand(String cmd) {
        super(cmd);
    }

    void specialisedFunction(TaskList tasks, Ui ui, Storage storage, int taskIndex){
        assert(taskIndex <= tasks.numOfTasks());
        boolean isChecked = tasks.fetchTask(taskIndex).check();
        if (isChecked) {
            ui.appendMessage(ui.getMarkedTaskMessage());
        } else {
            ui.appendMessage(ui.getAlreadyMarkedTaskMessage());
        }
        ui.appendMessage(tasks.fetchTask(taskIndex).toString());
    }
}
