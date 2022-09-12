package duke.command;

import duke.Message;
import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Deletes a task from the existing storage
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Creates a command to delete a task from the existing storage
     *
     * @param index the index of the task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Determines if the command should end the program for the user
     *
     * @return false by default
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the deletion of a task from the storage
     *
     * @param tasks the list of tasks to be modified in execution
     * @param ui the ui used to display messages to the user upon successful deletion
     * @param storage the storage to be modified in execution
     * @throws DukeException if the change cannot be saved in storage successfully or task cannot be deleted
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task deletedTask = tasks.deleteTaskAtPos(this.index);
            storage.save(tasks);
            ui.showDeleted(deletedTask);
        } catch (IndexOutOfBoundsException e) {
            if (tasks.getCount() == 0){
                throw new DukeException(Message.INVALID_ACCESS_EMPTY_TASKLIST);
            } else {
                throw new DukeException(Message.returnTaskNotFound(tasks));
            }
        }
    }

}
