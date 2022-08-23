package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * A Command which lets the user know that the command was invalid.
 */
public class InvalidCommand extends Command {
    /**
     * Displays the invalid command message for the Duke object.
     * @param taskList TaskList object of the Duke object for the Command object to use.
     * @param ui Ui object of the Duke object for the Command object to use.
     * @param storage Storage object of the Duke object for the Command object to use.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showInvalidCommand();
    }

    /**
     * Whether the InvalidTaskCommand should end the Duke object.
     * It should not.
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Checks if an object is equal to the InvalidCommand object.
     * InvalidCommand objects are equal if they are of the same type.
     * @param obj Target object to check equality.
     * @return True if the target object is of type InvalidCommand.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof InvalidCommand;
    }
}
