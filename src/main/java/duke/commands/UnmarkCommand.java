package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Sets a task as not done in the task list
 */
public class UnmarkCommand extends Command {

    protected int index;
    private final String MESSAGE =  "\tNoted! I have marked " +
            "the task as not done yet:";

    /**
     * Construct an unmark command
     *
     * @param index index of the task to marked as not done in task list
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.unmarkTask(index);
        storage.saveTaskList(taskList);
        String text = MESSAGE + "\n " + taskList.getTaskString(index);
        ui.displayMessage(text);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}