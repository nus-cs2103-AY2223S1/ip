package duke.command;

import duke.tasklist.TaskList;
import duke.utility.Storage;
import duke.utility.Ui;

/**
 * Represents command for help keyword to tell user
 * what commands are available
 */
public class HelpCommand extends Command {

    /**
     * Instantiates a new HelpCommand
     */
    public HelpCommand() {
        super("help");
    }

    /**
     * Executes the bye command to tell user
     * what commands are available
     *
     * @param tasks The list containing all the tasks
     * @param ui User interface for printing the message
     * @param storage To write and read from a text file
     * @return Returns String that contains message to be printed by gui
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.help();
    }
}
