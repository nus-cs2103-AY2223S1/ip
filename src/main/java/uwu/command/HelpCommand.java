package uwu.command;

import uwu.task.TaskList;
import uwu.uwu.Storage;
import uwu.uwu.Ui;

/**
 * Displays all of UwuBot's program instructions.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": displays all commands available.";

    /**
     * Executes the HelpCommand which is to list all UwuBot's program instructions.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out UwuBot's response.
     * @param storage The task list that is stored in the user's hard disk.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.displayCommands();
    }

    /**
     * Returns whether HelpCommand exits the program.
     *
     * @return false as HelpCommand does not exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
