package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * Represents a command to exit program.
 */
public class ExitCommand extends Command {
    private static final ExitCommand EXIT_COMMAND = new ExitCommand();

    /**
     * Constructor for ExitCommand class.
     */
    private ExitCommand() {
        super(true);
    }

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
     * @param commandOutputs       user interface of program.
     * @param storage  files storing task list.
     * @return
     */
    @Override
    public String execute(TaskList taskList, CommandOutputs commandOutputs, Storage storage) {
        System.exit(0); //placeholder method to end the application
        return commandOutputs.showGoodbye();
    }
}
