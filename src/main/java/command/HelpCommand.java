package command;

import clevernotbot.History;
import clevernotbot.Storage;
import clevernotbot.UI;
import exception.CleverNotBotException;
import task.TaskList;

/**
 * Represents a command used for helping user
 */
public class HelpCommand extends Command {

    /**
     * Constructor for the HelpCommand.
     *
     * @param commandName Description of command.
     * @param exit        Checking if program intends to exit.
     */
    public HelpCommand(String commandName, boolean exit) {
        super(commandName, exit);
    }

    /**
     * Runs the help command.
     *
     * @param tasks   The task list used to store all tasks.
     * @param textBox UI for the textbox.
     * @param storage The data where it is stored.
     * @param history All the history done for adding and removing stuff.
     * @return A String type response.
     * @throws CleverNotBotException Gives an exception.
     */
    @Override
    public String run(TaskList tasks, UI textBox, Storage storage, History history) throws CleverNotBotException {
        return "Open help window by pressing on\nHelp -> Command Help. "
                + "\nAlternatively, you may visit our User guide \nHelp -> Command Help -> Visit Help Page";
    }
}
