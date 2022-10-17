package duke.commands;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;


/**
 * Sets a task in the list as done
 */
public class MarkCommand extends Command {

    protected int index;
    private final String MESSAGE = "Excellent! I have marked " +
            "the task as done: ";
    private final String OUT_OF_BOUNDS_ERROR = "Please enter a positive integer " +
            "less than ";


    /**
     * Constrcuts a mark command
     *
     * @param index index of the task in the task list to be marked as done
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.markTask(index);
            storage.saveTaskList(taskList);
            String text = MESSAGE + "\n " + taskList.getTaskString(index);
            return ui.displayMessage(text);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(OUT_OF_BOUNDS_ERROR + taskList.getSize() + ".");
        }
    }

}