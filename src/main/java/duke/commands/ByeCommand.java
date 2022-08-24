package duke.commands;

import duke.managers.TaskManager;
import duke.managers.UiManager;

/**
 * Encapsulates a command for terminating the program. The command should be used as follows:
 * <ul>
 *     <li>
 *         {@code bye}
 *     </li>
 * </ul>
 *
 * @author Emily Ong Hui Qi
 */
public class ByeCommand implements Command {
    public static final String COMMAND_WORD = "bye";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    @Override
    public void execute(TaskManager taskManager, UiManager uiManager) {
        uiManager.print(ByeCommand.GOODBYE_MESSAGE);
    }

    /**
     * Returns {@code true} if and only if the supplied command is an instance of the {@link ByeCommand}.
     *
     * @param command The supplied command
     * @return {@code true} if and only if the supplied command is an instance of the {@link ByeCommand}
     */
    public static boolean is(Command command) {
        return command instanceof ByeCommand;
    }
}
