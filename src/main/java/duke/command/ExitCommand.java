package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * A Command which ends the Duke object.
 */
public class ExitCommand extends Command {
    /**
     * Displays the exit message for the Duke object.
     * @param taskList TaskList object of the Duke object for the Command object to use.
     * @param ui Ui object of the Duke object for the Command object to use.
     * @param storage Storage object of the Duke object for the Command object to use.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showBye();
    }

    /**
     * Whether the ExitCommand should end the Duke object.
     * It should.
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Checks if an object is equal to the ExitCommand object.
     * ExitCommand objects are equal if they are of the same type.
     * @param obj Target object to check equality.
     * @return True if the target object is of type ExitCommand.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExitCommand;
    }
}
