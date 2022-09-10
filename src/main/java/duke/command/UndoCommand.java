package duke.command;

import duke.model.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * A command to undo a previous change in the state of the tasklist.
 */
public class UndoCommand extends Command {

    private String lastUserInput;

    /**
     * A constructor for a UndoCommand.
     *
     * @param lastUserInput The last user input given.
     */
    public UndoCommand(String lastUserInput) {
        this.lastUserInput = lastUserInput;
    }

    /**
     * Executes a Command.
     *
     * @param taskList A list of tasks.
     * @param storage A location to store the task information.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        Boolean isReverted = taskList.revert();
        if (isReverted) {
            storage.writeToFile(taskList);
        }
        String response = Ui.undoCommandMessage(isReverted, this.lastUserInput);
        assert response != null : "response should not be null";
        return response;
    }
}
