package commands;

import duke.Storage;
import duke.Ui;
import tasks.TaskList;

/**
 * HelpCommand displays the list of commands available to the user.
 */
public class HelpCommand extends Command {

    /**
     * Constructor for HelpCommand
     */
    public HelpCommand() {
        super();
    }

    /**
     * Executes HelpCommand by displaying list of commands available.
     *
     * @param taskList Task list of program.
     * @param ui Ui to retrieve command list from.
     * @param s Storage of program.
     * @return List of possible commands.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage s) {
        return ui.printHelp();
    }
}
