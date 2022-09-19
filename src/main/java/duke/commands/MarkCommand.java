package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Sets a task in the list as done
 */
public class MarkCommand extends Command {

    protected int index;
    private final String MESSAGE = "\tExcellent! I have marked " +
            "the task as done: ";

    /**
     * Constrcuts a mark command
     *
     * @param index index of the task in the task list to be marked as done
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markTask(index);
        storage.saveTaskList(taskList);
        String text = MESSAGE + "\n " + taskList.getTaskString(index);
        ui.displayMessage(text);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}