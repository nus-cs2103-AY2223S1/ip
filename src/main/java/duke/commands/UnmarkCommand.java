package duke.commands;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Sets a task as not done in the task list
 */
public class UnmarkCommand extends Command {

    protected int index;
    private final String MESSAGE =  "Noted! I have marked " +
            "the task as not done yet:";
    private final String OUT_OF_BOUNDS_ERROR = "Please enter a positive integer " +
            "less than ";

    /**
     * Construct an unmark command
     *
     * @param index index of the task to marked as not done in task list
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.unmarkTask(index);
            storage.saveTaskList(taskList);
            String text = MESSAGE + "\n " + taskList.getTaskString(index);
            return ui.displayMessage(text);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(OUT_OF_BOUNDS_ERROR + taskList.getSize() + ".");
        }
    }

}