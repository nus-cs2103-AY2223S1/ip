package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;

/**
 * Representation of OnGoingCommand.
 */
public class OnGoingCommand extends Command {
    private final int index;

    public OnGoingCommand(int index) {
        this.index = index;
    }

    /**
     * Checks if this is an exit command.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the OnGoingCommand.
     * @param taskList TaskList to unmark Task at given index.
     * @param ui UI to print to users.
     * @param storage Storage to save and load TaskList.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        taskList.unMarkDone(index);
        String message = "OK, I've marked this task as not done yet:\n" + taskList.taskToString(this.index);
        ui.printWithDivider(message);
    }
    /**
     * Returns message from the OnGoingCommand.
     * @param taskList TaskList to unmark Task at given index.
     * @param ui UI to print to users.
     * @param storage Storage to save and load TaskList.
     */
    @Override
    public String getResponse(TaskList taskList, UI ui, Storage storage) {
        taskList.unMarkDone(index);
        String responseMessage = "OK, I've marked this task as not done yet:\n\t" + taskList.taskToString(this.index);
        return responseMessage;
    }
}
