package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
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
     * @throws ArrayIndexOutOfBoundsException if the deleteIndex is not provided by the user.
     * @throws IndexOutOfBoundsException if the deleteIndex exceeded the current existing number of Tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task removedTask = taskList.getList().get(this.deleteIndex);
            taskList.getList().remove(this.deleteIndex);

            String list = "";
            for (Task t : taskList.getList()) {
                list += t.toString();
            }
            storage.write(list);

            return "Noted. I've removed this task:\n " + ui.beautyWrapTask(removedTask)
                    + "\nNow you have " + taskList.getList().size() + " tasks in the list.\n";
        } catch (ArrayIndexOutOfBoundsException ex) {
            return "☹ OOPS!!! You did not specify which task to be delete.\n";
        } catch (IndexOutOfBoundsException ex) {
            return "☹ OOPS!!! Your list only has " + taskList.getList().size() + " tasks.\n";
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
