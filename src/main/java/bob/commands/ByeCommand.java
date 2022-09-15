package bob.commands;

import bob.Storage;
import bob.TaskList;
import bob.Ui;

/**
 * ByeCommand class to handle "bye" keyword
 */
public class ByeCommand extends Command {

    /**
     * Constructor for ByeCommand
     */
    public ByeCommand() {
        super();
    }

    /**
     * Method to return whether bot should be terminated
     *
     * @return true for ByeCommand
     */
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String executeCommand(TaskList taskList, Storage storage, Ui ui) {
        return ui.sayGoodbye();
    }
}
