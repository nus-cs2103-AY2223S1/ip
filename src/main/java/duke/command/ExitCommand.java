package duke.command;

import duke.ClientList;
import duke.task.TaskList;

/**
 * Represents a command to exit program.
 */
public class ExitCommand extends Command {
    private static final ExitCommand EXIT_COMMAND = new ExitCommand();

    /**
     * Returns the exit command.
     *
     * @return exit command.
     */
    public static ExitCommand of() {
        return EXIT_COMMAND;
    }

    /**
     * Closes program.
     *
     * @param taskList task list.
     * @param clientList client list.
     * @return String representation of goodbye.
     */
    @Override
    public String execute(TaskList taskList, ClientList clientList) {
        return CommandOutputs.showGoodbye();
    }
}
