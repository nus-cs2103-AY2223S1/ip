package duke.commands;

import duke.managers.TaskManager;
import duke.managers.UiManager;

/**
 * Encapsulates a command that represents an incorrect usage of the application.
 *
 * @author Emily Ong Hui Qi
 */
public class IncorrectCommand implements Command {
    private static final String UNKNOWN_COMMAND_ERROR = "I do not understand your command!";

    @Override
    public void execute(TaskManager taskManager, UiManager uiManager) {
        uiManager.print(IncorrectCommand.UNKNOWN_COMMAND_ERROR);
    }
}
