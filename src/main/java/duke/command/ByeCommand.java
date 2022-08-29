package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to end the Duke program.
 * Inherits from Command abstract class.
 */
public class ByeCommand extends Command {

    /**
     * Executes the command given.
     * Command will be set to end the program.
     * @param tasks The list that contains all the Tasks on the program.
     * @param ui Deals with the interaction with user.
     * @param storage Deals with the loading and updating of file.
     * @return The String response of Duke after running command.
     */
    @Override
    public String run(TaskList tasks, Ui ui, Storage storage) {
        super.endApp();
        return ui.printExit();
    }
}
