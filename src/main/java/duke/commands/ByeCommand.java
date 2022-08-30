package duke.commands;

import duke.managers.TaskManager;

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

    private static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";

    /**
     * Returns {@code true} if and only if the supplied command is an instance of the {@link ByeCommand}.
     *
     * @param command The supplied command
     *
     * @return {@code true} if and only if the supplied command is an instance of the {@link ByeCommand}
     */
    public static boolean is(Command command) {
        return command instanceof ByeCommand;
    }

    @Override
    public String execute(TaskManager taskManager) {
        return ByeCommand.MESSAGE_GOODBYE;
    }
}
