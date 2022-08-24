package duke.commands;

import duke.managers.TaskManager;
import duke.managers.UiManager;

/**
 * TODO: Add JavaDocs
 */
public class ByeCommand implements Command {
    public static final String COMMAND_WORD = "bye";

    private static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";

    public static boolean is(Command command) {
        return command instanceof ByeCommand;
    }

    @Override
    public void execute(TaskManager taskManager, UiManager uiManager) {
        uiManager.print(ByeCommand.MESSAGE_GOODBYE);
    }
}
