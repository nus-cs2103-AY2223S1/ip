package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a Bye Command that inherits from
 * the abstract class Command.
 */
public class ByeCommand extends Command {
    protected String commandLine;

    /**
     * Constructor for this that takes in a
     * commandLine as a String.
     *
     * @param commandLine Command from the user.
     */
    public ByeCommand(String commandLine) {
        this.commandLine = commandLine;
    }

    /**
     * Returns a boolean true to show that this
     * is the last command.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the command and prints a goodbye
     * string to the user.
     *
     * @param taskList The ArrayList that contains all the tasks.
     * @param ui The Ui that interacts with the next command from the user.
     * @param storage The Storage used to store the tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbye();
    }
}
