package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Exit the program
 */
public class ExitCommand extends Command {
    public static final boolean IS_EXIT = true;
    public static final String GOODBYE_MESSAGE = "Bye. Please don't leave me :( Hope to see you again soon!";


    /**
     * Constructs a ExitCommand instance without initiating any parameter.
     */
    public ExitCommand() {
    }

    /**
     * Throw InvalidCommandException and to show user the Duke cannot process it.
     *
     * @param taskList unused for EmptyCommand.
     * @param ui unused for EmptyCommand.
     * @param storage unused for EmptyCommand.
     *
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return GOODBYE_MESSAGE;
    }

    /**
     * Returns true as Exit is a terminating Command.
     *
     * @return true.
     */
    public boolean isExit() {
        return this.IS_EXIT;
    }
}
