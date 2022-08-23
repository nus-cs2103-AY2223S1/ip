package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;

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
     * @param ui user interface of program.
     * @param storage files storing task list.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.showGoodbye();
    }
}
