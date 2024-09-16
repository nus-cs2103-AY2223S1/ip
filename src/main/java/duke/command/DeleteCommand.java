package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;


/**
 * Delete Tasks from TaskList.
 */
public class DeleteCommand extends Command {
    public static final boolean IS_EXIT = false;
    public final int deleteIndex;

    /**
     * Constructs a DeleteCommand instance with the provided deleteIndex numbering
     * the corresponding Task to be deleted.
     *
     * @param deleteIndex the numbered Task which is to be deleted.
     */
    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    /**
     * Remove the Task at deleteIndex from taskList.
     *
     * @param taskList the TaskList where the corresponding Task to be deleted from.
     * @param ui the Ui to assist the conversion of Task to String.
     * @param storage the Storage to remove new Task from file.
     * @throws DukeException if the deleteIndex is not provided by the user or deleteIndex is out of bound.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Task removedTask = taskList.getTask(this.deleteIndex);
            taskList.removeTask(this.deleteIndex);

            String list = "";
            for (Task t : taskList.getList()) {
                list += t.toString();
            }
            storage.write(list);

            return "Noted. I've removed this task:\n " + ui.beautyWrapTask(removedTask)
                    + "\nNow you have " + taskList.getSize() + " tasks in the list.\n";
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("Your list only has " + taskList.getSize() + " tasks.\n");
        }
    }

    /**
     * Returns false as Delete is not a terminating Command.
     *
     * @return false.
     */
    public boolean isExit() {
        return this.IS_EXIT;
    }

}
