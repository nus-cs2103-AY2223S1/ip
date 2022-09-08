package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.task.Task;

/**
 * Representation of the DeleteCommand.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructor of DeleteCommand.
     * @param index index of deleted task.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the DeleteCommand.
     * @param taskList TaskList to delete Tasks at given index.
     * @param ui UI to print to users.
     * @param storage Storage to save and load TaskList.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        Task toRemove = taskList.remove(this.index);
        String message = "Noted. I've removed this task:\n" + toRemove + "\nNow you have "
                + taskList.getSize() + " tasks in the list.";
        ui.printWithDivider(message);
    }
    @Override
    public String getResponse(TaskList taskList, UI ui, Storage storage) {
        Task toRemove = taskList.remove(this.index);
        String responseMessage = "Noted. I've removed this task:\n\t" + toRemove + "\nNow you have "
                + taskList.getSize() + " tasks in the list.";
        return responseMessage;
    }
}
