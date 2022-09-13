package duke.command;

import duke.FileStorage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Command used to list all the available commands.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    /**
     * Returns the message of all available commands to the GUI.
     *
     * @param list The taskList of Duke.
     * @param storage The fileStorage of Duke.
     * @param ui The Ui of Duke.
     * @return The message meant for the GUI.
     */
    @Override
    public String execute(TaskList list, FileStorage storage, Ui ui) {
        return ui.getHelpMessage();
    }
}
