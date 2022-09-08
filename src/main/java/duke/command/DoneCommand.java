package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;

/**
 * Representation for the done command.
 */
public class DoneCommand extends Command {
    private final int indexToMarkAsDone;

    /**
     * Constructor for DoneCommand.
     * @param indexToMarkAsDone index of taskList to mark as done.
     */
    public DoneCommand(int indexToMarkAsDone) {
        this.indexToMarkAsDone = indexToMarkAsDone;
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
     * Executes the DoneCommand.
     * @param taskList TaskList to mark Task at given index as done.
     * @param ui UI to print to users.
     * @param storage Storage to save and load TaskList.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        taskList.markAsDone(this.indexToMarkAsDone);
        String message = "Nice! I've marked this task as done:\n" + taskList.taskToString(this.indexToMarkAsDone);
        ui.printWithDivider(message);
    }
    @Override
    public String getResponse(TaskList taskList, UI ui, Storage storage) {
        taskList.markAsDone(this.indexToMarkAsDone);
        String responseMessage = "Nice! I've marked this task as done:\n\t"
                + taskList.taskToString(this.indexToMarkAsDone);
        return responseMessage;
    }
}
