package duke.command;

import duke.ClientList;
import duke.Storage;
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
     * @param storage  files storing task list.
     * @param clientList client list.
     * @return String representation of goodbye.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, ClientList clientList) {
        System.exit(0); //placeholder method to end the application
        return CommandOutputs.showGoodbye();
    }
}
