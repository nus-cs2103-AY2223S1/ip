package duke.commands;

import duke.managers.TaskManager;
import duke.managers.UiManager;

/**
 * TODO: Add JavaDocs
 */
public class IncorrectCommand implements Command {
    private static final String ERROR_UNKNOWN_COMMAND = "I do not understand your command!";

    @Override
    public void execute(TaskManager taskManager, UiManager uiManager) {
        uiManager.print(IncorrectCommand.ERROR_UNKNOWN_COMMAND);
    }
}
