package duke.commands;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;


/**
 * Deletes a command from the tasklist.
 */
public class DeleteCommand extends Command {

    protected int index;
    private final String MESSAGE = "Noted. I've remove this task: ";
    private final String OUT_OF_BOUNDS_ERROR = "Please enter a positive integer " +
            "less than ";

    /**
     * Constructs a delete command
     *
     * @param index index of the task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = taskList.deleteTask(index);
            storage.saveTaskList(taskList);
            String text = MESSAGE + "\n" + task.toString() +
                    "\n" + taskList.displayNumTasks();
            return ui.displayMessage(text);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(OUT_OF_BOUNDS_ERROR + taskList.getSize() + ".");
        }
    }


}